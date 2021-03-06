package models

import scala.util.parsing.combinator._

import org.json4s._
import org.json4s.native.JsonMethods._
import org.json4s.native.Serialization
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.json._
import models.JsonAnswerFormats._

/**
 * Trait Question. It's the base class for all the question types
 */
sealed trait Question{
    
    def category: String
    def title : String 
    def wording: String
    
    
    
  }

/**
 * Formats needed for JSON serialization/deserialization
 */
 object JsonQuestionFormats {
  implicit val singleChoiceQuestionFormat = Json.format[SingleChoiceQuestion]
  implicit val multipleChoiceQuestionFormat = Json.format[MultipleChoiceQuestion]
  implicit val booleanQuestionFormat = Json.format[BooleanQuestion]
  implicit val matchingQuestionFormat = Json.format[MatchingQuestion]
 }
  
 /**
  * Object Question contains the serialization/deserialization information for Question
  */
  object Question{
    
    import play.api.libs.json.Json
  import play.api.data._
  
 
   
    
    implicit object questionFormat extends Format[Question]{
      override def reads(json: JsValue) = JsSuccess(
          (json \ "options") match {
            case bq: JsUndefined => BooleanQuestion((json \ "category").as[String],
                (json \ "title").as[String], 
                (json \ "wording").as[String],
                (json \ "answer").as[BooleanAnswer])
           case a: JsValue => {
              val options = (json \ "options").as[Seq[Answer]]
              options.head match {
                case WeightedAnswer(a,b,c) => MultipleChoiceQuestion((json \ "category").as[String],
                    (json \ "title").as[String], (json \ "wording").as[String], 
                    options.map{x => x.asInstanceOf[WeightedAnswer]})
                case o: SingleChoiceAnswer => SingleChoiceQuestion((json \ "category").as[String],
                    (json \ "title").as[String], (json \ "wording").as[String],
                    options.map{x => x.asInstanceOf[SingleChoiceAnswer]})
                case MatchingAnswer(wor, com, opts) => MatchingQuestion((json \ "category").as[String],
                    (json \ "title").as[String], (json \ "wording").as[String],
                    options.map{x => x.asInstanceOf[MatchingAnswer]})
              }
            }
              
              
            
          }
          )
          
      override def writes(q: Question) : JsValue = JsObject(Seq(
          "category" -> JsString(q.category),
          "title" -> JsString(q.title),
          "wording" -> JsString(q.wording),
          q match {
            case SingleChoiceQuestion(_,_,_,o) => "options" -> Json.toJson(o) 
            case MultipleChoiceQuestion(_,_,_,o) => "options" -> Json.toJson(o)
            case BooleanQuestion(_,_,_,cAns) => "answer" -> Json.toJson(cAns)
            case MatchingQuestion(_,_,_,opts) => "options" -> Json.toJson(opts)
                 
            
          }))
    }
    
  
  
    /**
     * Load Questions from a QTI file
     * @param node: The XML source
     */
    def fromXML(node: scala.xml.Node): Seq[Question] = {
      
      var questions = Seq[Question]()
      (node \ "testPart" \ "assessmentSection" \ "assessmentItemRef").map { x => {
          questions = questions :+ fromXMLQuestionFile(xml.XML.loadFile((x \ "@href").text))
      }}
      questions
      
    }
    
   
    /**
     * Loads a Question from a QTI file (Each QTI file contains one question)
     * @param node The XML source
     * @returns a question
     */
   def fromXMLQuestionFile(node: scala.xml.Node): Question = {
     
       val questionType = (node \ "responseDeclaration" \ "@cardinality").text
       
       if(questionType.equals("single")){
        readSimpleQuestion(node)
      
      }
       else if(questionType.equals("multiple")){

         readMultipleChoiceQuestion(node)

       }
      
       else{
         //NOT IMPLEMENTED
         //TODO
         BooleanQuestion("","","", BooleanAnswer("", false)) 
       }
   }
   
   /**
    * Loads a MultipleChoiceQuestion from the lines of a XML file
    * @param node The xml node 
    * @returns a multiple choice question
    */
   def readMultipleChoiceQuestion(node: scala.xml.Node): Question = {
     val correct = (node \ "responseDeclaration" \ "correctResponse" \ "value").map(
             x => x.text)
         val mapping = (node \ "responseDeclaration" \ "mapping")
        
         
         val weights = (mapping \ "mapEntry") 
            .map{x => Map((x \ "@mapKey").text -> (x \ "@mappedValue").text.toInt)}
            .reduceLeft(_ ++ _)
            
         val lowerBound = (mapping \ "@lowerBound").text.toInt
         val upperBound = (mapping \ "@upperBound").text.toInt
         val defaultWeight = (mapping \ "@defaultValue").text.toInt
         val wording = (node \ "itemBody" \ "choiceInteraction" \ "prompt").text
         val opts = (node \ 
               "itemBody" \ "choiceInteraction" \ "simpleChoice").map{ x => {
                 val id = (x \ "@identifier").text
                 
                 weights get id match {
                   case Some(weight) => WeightedAnswer(x.text, "", 
                       (weight.toFloat / (lowerBound + upperBound)*100).toInt )
                   case None => WeightedAnswer(x.text, "", 
                       (defaultWeight.toFloat / (lowerBound + upperBound)*100).toInt)
                 }
               }}
         MultipleChoiceQuestion("",wording, "", opts)
   }
   
   /**
    * Reads a single choice question from the lines of a QTI file
    * 
    * @param node The XML source
    * @returns a single choice question
    */
   def readSimpleQuestion(node: scala.xml.Node): Question = {
          val correct = (node \ "responseDeclaration" \ "correctResponse" \ "value").text
      
        val opts = (node \ 
               "itemBody" \ "choiceInteraction" \ "simpleChoice")
                .map { x => {
          val id = (x \ "@identifier").text
          if(id.equals(correct))
            CorrectAnswer(x.text, "")
          else IncorrectAnswer(x.text, "")
        }.asInstanceOf[SingleChoiceAnswer] }
        SingleChoiceQuestion("",(node \ "itemBody" \ "choiceInteraction" \ "prompt").text,
           "",
           opts)
   }

  }
  
  /**
   * SingleChoiceQuestion is a class that represents questions with only one right answer
   * 
   * @param category The category of the question
   * @param title The question title
   * @param wording The question wording
   * @param options The answers that take part of the question
   */
  case class SingleChoiceQuestion(category: String, title: String, wording: String, options: Seq[SingleChoiceAnswer]) extends Question{
    def toXML() = <responseDeclaration identifier="RESPONSE" cardinality="single" baseType="identifier">
  <correctResponse>
		<value>
   {options.find { x => x.correct }.get.wording}
    </value>
  </correctResponse>
</responseDeclaration>
<outcomeDeclaration identifier="SCORE" cardinality="single" baseType="float">
	<defaultValue>
	<value>0</value>
	</defaultValue>
</outcomeDeclaration>
<itemBody>
	<p>{this.title}</p>
  <choiceInteraction responseIdentifier="RESPONSE" shuffle="false" maxChoices="1">
			<prompt>{this.wording}</prompt>
			{options.map(x => <simpleChoice identifier={x.wording}></simpleChoice>)}
	</choiceInteraction>
</itemBody>
      
 
  }
  /**
   * A MatchingQuestion is a questions where the answers have to be matched
   * 
   * @param category The category of the question
   * @param title The question title
   * @param wording The question wording
   * @param options The answers that take part of the question
   */
  case class MatchingQuestion(category: String, title: String, wording: String, options: Seq[MatchingAnswer]) extends Question
  
  /**
   * A MultipleChoiceQuestion is a question where each answer has an associated weight.
   * 
   * @param category The category of the question
   * @param title The question title
   * @param wording The question wording
   * @param options The answers that take part of the question
   */
  case class MultipleChoiceQuestion(category: String, title: String, wording: String, options: Seq[WeightedAnswer]) extends Question
  
  /**
   * A BooleanQuestion is a question where the answers have to be matched
   * 
   * @param category The category of the question
   * @param title The question title
   * @param wording The question wording
   * @param answer The solution to the question (BooleanAnswer that is True or False)
   */
  case class BooleanQuestion(category: String, title: String, wording: String, answer: BooleanAnswer) extends Question
 