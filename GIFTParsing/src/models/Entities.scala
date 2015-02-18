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
  
  object JsonFormats{
  
  import play.api.libs.json.Json
  import play.api.data._
  
  

  implicit val correctAnswerFormat = Json.format[CorrectAnswer]
  implicit val incorrectAnswerFormat = Json.format[IncorrectAnswer]
  implicit val questionFormat = Json.format[Question]
 
}
