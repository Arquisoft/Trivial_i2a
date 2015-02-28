package models

import scala.util.parsing.combinator._
import org.json4s._
import org.json4s.native.JsonMethods._
import org.json4s.native.Serialization
import reactivemongo.api.MongoDriver
import reactivemongo.bson._
import play.modules.reactivemongo.json.collection._
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.json._

/**
 * @author rubcuevas
 */
sealed trait Answer{
  def comment: String
  
		
}

  
  
  object Answer{
  implicit object answerFormat extends Format[Answer]{
    
    override def reads(json: JsValue) = JsSuccess(
      (json \ "correct") match {
        case und: JsUndefined => (json \ "answer") match{
          case wa : JsUndefined => WeightedAnswer(
            (json \ "wording").as[String], (json \ "comment").as[String],
            (json \ "weight").as[Int]
            )
          case ba: JsValue => BooleanAnswer((json \ "comment").as[String],
              (json \ "answer").as[Boolean])
        }
        case b: JsValue => {
          (json \ "correct").as[Boolean] match{
            case true => CorrectAnswer((json \ "wording").as[String], (json \ "comment").as[String])
            case false => IncorrectAnswer((json \ "wording").as[String], (json \ "comment").as[String])
          }
        }
      
      }
        )
      
    
    override def writes(a: Answer): JsValue = JsObject(
       //"wording" -> JsString(a.wording),
      
       a match{
         case b: WeightedAnswer => Seq("weight" -> JsNumber(b.weight), "comment" -> JsString(b.comment),
             "wording" -> JsString(b.wording))
         case c: SingleChoiceAnswer => Seq(
             "correct" -> JsBoolean(c.correct), 
             "wording" -> JsString(c.wording), "comment" -> JsString(c.wording))
         case d: BooleanAnswer => Seq("answer" -> JsBoolean(d.answer), "comment" -> JsString(d.comment))
       }
    )
  }
  
      
  }
    
 sealed trait NonWeightedAnswer extends Answer
   
  object NonWeightedAnswer{
    implicit object nonWeightedAnswer extends Format[NonWeightedAnswer]{
      override def reads(json: JsValue) = JsSuccess({
        (json \ "correct") match{
          case ba : JsUndefined => BooleanAnswer((json \ "comment").as[String], (json \ "answer").as[Boolean])
          case b: JsValue => {
          (json \ "correct").as[Boolean] match{
            case true => CorrectAnswer((json \ "wording").as[String], (json \ "comment").as[String])
            case false => IncorrectAnswer((json \ "wording").as[String], (json \ "comment").as[String])
          }
        }
        }
      })
    
    
    override def writes(a: NonWeightedAnswer): JsValue = JsObject(Seq(
       //"wording" -> JsString(a.wording),
       "comment" -> JsString(a.comment),
       a match{
         case c: SingleChoiceAnswer => "correct" -> JsBoolean(c.correct)
         case d: BooleanAnswer => "answer" -> JsBoolean(d.answer)
       }
    ))
    }
  }
  
  case class WeightedAnswer(wording: String, comment : String, weight: Int)
    extends Answer
    
    
     
  
    /*
   object WeightedAnswer{
    implicit object WeightedAnswer extends Writes[WeightedAnswer]{
      override def writes(w: WeightedAnswer) : JsValue = JsObject(Seq(
        "wording" -> JsString(w.wording),
        "comment" -> JsString(w.comment),
        "weight" -> JsNumber(w.weight)
      ))
    }
  }
*/
  sealed trait SingleChoiceAnswer extends NonWeightedAnswer{
        def wording: String
        def correct : Boolean 
    } 
  
  object SingleChoiceAnswer{
    implicit object singleChoiceAnswerFormat extends Format[SingleChoiceAnswer]{
      override def reads(json: JsValue) = JsSuccess({
        (json \ "correct").as[Boolean] match{
          case true => CorrectAnswer((json \ "title").as[String], (json \ "wording").as[String])
          case false => IncorrectAnswer((json \ "title").as[String], (json \ "wording").as[String])
        }
      })
      
    override def writes(s: SingleChoiceAnswer) = JsObject(Seq(
        "wording" -> JsString(s.wording),
        "comment" -> JsString(s.comment),
        "correct" -> JsBoolean(s.correct)))
    }
  }
  
  case class BooleanAnswer(comment: String, answer: Boolean) extends NonWeightedAnswer

    
  case class CorrectAnswer(wording : String, comment : String, correct: Boolean = true) 
    extends SingleChoiceAnswer 

  
  case class IncorrectAnswer(wording : String, comment : String, correct: Boolean = false)
    extends SingleChoiceAnswer
    
  
  
  
  sealed trait Question{
    def title : String 
    def wording: String
    //def options : Seq[Answer]
    
    
  }
  
  object Question{
    
    import play.api.libs.json.Json
  import play.api.data._
  
  

 // implicit val correctAnswerFormat = Json.format[CorrectAnswer]
 // implicit val incorrectAnswerFormat = Json.format[IncorrectAnswer]
  //implicit val questionFormat = Json.format[Question]
  implicit val weightedAnswerFormat = Json.format[WeightedAnswer]
  implicit val weightedAnswerReads = Json.reads[WeightedAnswer]
  implicit val booleanAnswerFormat = Json.format[BooleanAnswer]
  implicit val singleChoiceQuestionFormat = Json.format[SingleChoiceQuestion]
  implicit val multipleChoiceQuestionFormat = Json.format[MultipleChoiceQuestion]
  implicit val booleanQuestionFormat = Json.format[BooleanQuestion]
  
 
   
    
    implicit object questionFormat extends Format[Question]{
      override def reads(json: JsValue) = JsSuccess(
          (json \ "options") match {
            case bq: JsUndefined => BooleanQuestion((json \ "title").as[String], 
                (json \ "wording").as[String],
                (json \ "answer").as[BooleanAnswer])
           case a: JsValue => {
              val options = (json \ "options").as[Seq[Answer]]
              val option = options.head
              option match {/*
                case wq : JsUndefined => MultipleChoiceQuestion(
                    (json \ "title").as[String], (json \ "wording").as[String], (json \ "options").as[Seq[WeightedAnswer]])
                case scq: JsValue => SingleChoiceQuestion(
                    (json \ "title").as[String], (json \ "wording").as[String], (json \ "options").as[Seq[SingleChoiceAnswer]])
                */
                case WeightedAnswer(a,b,c) => MultipleChoiceQuestion(
                    (json \ "title").as[String], (json \ "wording").as[String], 
                    options.map{x => x.asInstanceOf[WeightedAnswer]})
                case o: SingleChoiceAnswer => SingleChoiceQuestion(
                    (json \ "title").as[String], (json \ "wording").as[String],
                    options.map{x => x.asInstanceOf[SingleChoiceAnswer]})
              }
            }
              
              
            
          }
          )
          
      override def writes(q: Question) : JsValue = JsObject(Seq(
          "title" -> JsString(q.title),
          "wording" -> JsString(q.wording),
          q match {
            case SingleChoiceQuestion(_,_,o) => "options" -> Json.toJson(o) 
            case MultipleChoiceQuestion(_,_,o) => "options" -> Json.toJson(o)
            case BooleanQuestion(_,_,cAns) => "answer" -> Json.toJson(cAns)
                 
            
          }))
    }
    
  
  
    
    def fromXML(node: scala.xml.Node): Seq[Question] = {
      
      var questions = Seq[Question]()
      (node \ "testPart" \ "assessmentSection" \ "assessmentItemRef").map { x => {
          questions = questions :+ fromXMLQuestionFile(xml.XML.loadFile((x \ "@href").text))
      }}
      questions
      
    }
    
   
    
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
         BooleanQuestion("","", BooleanAnswer("", false)) 
       }
   }
   
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
         MultipleChoiceQuestion(wording, "", opts)
   }
   
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
        SingleChoiceQuestion((node \ "itemBody" \ "choiceInteraction" \ "prompt").text,
           "",
           opts)
   }

  }
  case class SingleChoiceQuestion(title: String, wording: String, options: Seq[SingleChoiceAnswer]) extends Question{
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
  case class MultipleChoiceQuestion(title: String, wording: String, options: Seq[WeightedAnswer]) extends Question
  case class BooleanQuestion(title: String, wording: String, answer: BooleanAnswer) extends Question
  object JsonFormats{
  
  implicit val correctAnswerFormat = Json.format[CorrectAnswer]
  implicit val incorrectAnswerFormat = Json.format[IncorrectAnswer]
 
}
