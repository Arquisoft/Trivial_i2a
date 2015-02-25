

/**
 * @author rubcuevas
 */

package inputLanguages

import scala.util.parsing.combinator._
import org.json4s._
import org.json4s.native.JsonMethods._
import org.json4s.native.Serialization
import reactivemongo.api.MongoDriver
import reactivemongo.bson._
import play.modules.reactivemongo.json.collection._
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.json._
import models._
import inputLanguages._

  trait GIFT extends InputLanguage with JavaTokenParsers {

  
   def questions: Parser[Seq[Question]] = rep(question) ^^ {_.toSeq}
    def question : Parser[Question] = opt(comment)~>opt(questionTitle)~questionWording~options ^^ 
    {case (None~q~o) => o(0) match{
      case a: SingleChoiceAnswer => SingleChoiceQuestion("", q, o.map { x => x.asInstanceOf[SingleChoiceAnswer] }.toSeq)
      case b: WeightedAnswer => MultipleChoiceQuestion("", q, o.map { x => x.asInstanceOf[WeightedAnswer] }.toSeq)
      case c: BooleanAnswer => BooleanQuestion("", q, c)
    }
    case (t~q~o) => o(0) match{
      case a: SingleChoiceAnswer => SingleChoiceQuestion("", q, o.map { x => x.asInstanceOf[SingleChoiceAnswer] }.toSeq)
      case b: WeightedAnswer => MultipleChoiceQuestion("", q, o.map { x => x.asInstanceOf[WeightedAnswer] }.toSeq)
      case c: BooleanAnswer => BooleanQuestion("", q, c)}
      
    }
    def allChars : Parser[String] = "[A-Za-z0-9 ¿?!¡@\"¨'%&$#*+-\\[\\]\\(\\);:,]*".r ^^{_.toString}
   def comment: Parser[String] = allChars ^^ {_.toString}
    def questionTitle : Parser[String] = "::"~>allChars<~"::"
    def questionWording : Parser[String] = allChars ^^ {_.toString}
    def options : Parser[Seq[Answer]] = "{"~>rep(option)<~"}" ^^{_.toSeq}
    def option : Parser[Answer] = correctAnswer | wrongAnswer | weightedAnswer | booleanAnswer
    def correctAnswer: Parser[CorrectAnswer] = 
      correctAnswerWording~opt(answerComment) ^^
        {case (wording~Some(comment)) => CorrectAnswer(wording, comment)
        case(wording~None) => CorrectAnswer(wording, "")}
    def wrongAnswer: Parser[IncorrectAnswer] = 
        wrongAnswerWording~opt(answerComment) ^^
        {case (wording~None) => IncorrectAnswer(wording, "")
        case(wording~Some(comment)) => IncorrectAnswer(wording, comment)}
    def weightedAnswer : Parser[Answer] = percentage~answerWording~opt(answerComment) ^^{
      case (percentage~wording~None) => WeightedAnswer(wording, "", percentage)
      case(percentage~wording~Some(comment)) => WeightedAnswer(wording, comment, percentage)
    }
    def booleanAnswer : Parser[BooleanAnswer] = (booleanStatement)~opt(answerComment) ^^ {
      case (bs~Some(com)) => if(bs.equals("T") || bs.equals("TRUE")) BooleanAnswer(com,true) else BooleanAnswer(com, false)
      case(bs~None) => if(bs.equals("F") || bs.equals("FALSE")) BooleanAnswer("",true) else BooleanAnswer("", false)
    }
    def answerWording: Parser[String] = allChars ^^{_.toString}
    def correctAnswerWording: Parser[String] = "="~>answerWording ^^(_.toString)
    def wrongAnswerWording : Parser[String] = "~"~>"""[^%].*""".r ^^(_.toString)
    def answerComment : Parser[String] = "#"~>allChars ^^ (_.toString)
    def percentage : Parser[Int] = "~%"~>"[-]{0,1}[0-9]+".r<~"%" ^^{ _.toInt}
    def trueStatement : Parser[String] = "T\\b".r | "TRUE\\b".r
    def falseStatement : Parser[String] = "F\\b".r | "FALSE\\b".r
    def booleanStatement : Parser[String] = trueStatement | falseStatement
   
}



object TestGIFT extends GIFT
{
  
  //implicit val formats = Serialization.formats(NoTypeHints)

        
 def main(){
    val testObj = """
~%100%No one
~%50%Grant
~%50%Grants wife
~%100%Grants father
"""


   
    println(parse(option, testObj))
  }
}
//       val driver = new MongoDriver
//       val connection = driver.connection(List("localhost"))
//       val db = connection.db("trivial")
//       val collection : JSONCollection = db.collection[JSONCollection]("questions")
//       def main() = {
//        // val json = write(parse(questions, testObj).get)
//         val a1 = CorrectAnswer("pregunta1", "hey")
//         val a2 = IncorrectAnswer("pregunta2", "Comment to pregunta2")
//         val a3 = IncorrectAnswer("pregunta3", "Comment to pregunta3")
//         val opts = List(a1, a2, a3)
//         val prueba = Question("theWording", opts)
         
        
             
          
         
         
//  
//        
//  }
//
//}



