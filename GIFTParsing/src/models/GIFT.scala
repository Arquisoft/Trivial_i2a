

/**
 * @author rubcuevas
 */

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

sealed trait Answer{
  def wording: String
  def comment: String
  def correct: Boolean
}
  
  
  object Answer{
  implicit object answerFormat extends Format[Answer]{
    
    override def reads(json: JsValue) = JsSuccess(
      (json \ "correct").as[Boolean] match {
        case true => new CorrectAnswer(
            (json \ "wording").as[String], (json \ "comment").as[String],
            (json \ "correct").as[Boolean])
        case false => new IncorrectAnswer((json \ "wording").as[String], (json \ "comment").as[String])
      }
      )
    
     
    
    
    override def writes(a: Answer): JsValue = JsObject(Seq(
       "wording" -> JsString(a.wording),
       "comment" -> JsString(a.comment),
       "correct" -> JsString(a.correct.toString())
      
    ))
  }
}
  case class CorrectAnswer(wording : String, comment : String, correct: Boolean = true) 
    extends Answer

  
  case class IncorrectAnswer(wording : String, comment : String, correct: Boolean = false)
    extends Answer
  case class Question(wording: String, options : Seq[Answer])
  

class GIFT extends JavaTokenParsers {


  
    def correctAnswerWording: Parser[String] = "="~>"[a-zA-z0-9_ ]*".r ^^(_.toString)
    def wrongAnswerWording : Parser[String] = "~"~>"[a-zA-z0-9_ ]*".r ^^(_.toString)
    def answerComment : Parser[String] = "#"~>"[a-zA-z0-9_ ]*".r ^^ (_.toString)
    def correctAnswer: Parser[CorrectAnswer] = 
      correctAnswerWording~answerComment ^^
        {case (wording~comment) => CorrectAnswer(wording, comment)}
    def wrongAnswer: Parser[IncorrectAnswer] = 
        wrongAnswerWording~answerComment ^^
        {case (wording~comment) => IncorrectAnswer(wording, comment)}
    def option : Parser[Answer] = correctAnswer | wrongAnswer
    def questionWording : Parser[String] = "::"~>"[a-zA-z0-9?_ ]*".r ^^ (_.toString)
    def options : Parser[Seq[Answer]] = "{"~>rep(option)<~"}" ^^{_.toSeq}
    def question : Parser[Question] = questionWording~options ^^ 
    {case (q~o) => Question(q, o)}
   
    def questions: Parser[Seq[Question]] = rep(question) ^^ {_.toSeq}
}

object JsonFormats{
  
  import play.api.libs.json.Json
  import play.api.data._
  
  

  implicit val correctAnswerFormat = Json.format[CorrectAnswer]
  implicit val incorrectAnswerFormat = Json.format[IncorrectAnswer]
  implicit val questionFormat = Json.format[Question]
 
}


object TestGIFT extends GIFT
{
  implicit val formats = Serialization.formats(NoTypeHints)
        val testObj : String = """::Who is buried in Grant's tomb in New York City?{
=Grant
#Yesss
~Ruben
#Noooooo
}
::This is another question{
~This is not the answer
#This is a comment to the wrong answer
=This is the right answer
#YESSSSSS
}"""
       val driver = new MongoDriver
       val connection = driver.connection(List("localhost"))
       val db = connection.db("trivial")
       val collection : JSONCollection = db.collection[JSONCollection]("questions")
       def main() = {
        // val json = write(parse(questions, testObj).get)
         val a1 = CorrectAnswer("pregunta1", "hey")
         val a2 = IncorrectAnswer("pregunta2", "Comment to pregunta2")
         val a3 = IncorrectAnswer("pregunta3", "Comment to pregunta3")
         val opts = List(a1, a2, a3)
         val prueba = Question("theWording", opts)
         
        
             
          
         
         
  
        
  }

}




