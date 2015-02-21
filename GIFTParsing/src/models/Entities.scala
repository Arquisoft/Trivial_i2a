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
}
  
  
  object Answer{
  implicit object answerFormat extends Format[Answer]{
    
    override def reads(json: JsValue) = JsSuccess(
      (json \ "correct") match {
        case a: JsUndefined => WeightedAnswer(
            (json \ "wording").as[String], (json \ "comment").as[String],
            (json \ "weight").as[Int]
            )
        case b: JsValue => {
          (json \ "correct").as[Boolean] match{
            case true => CorrectAnswer((json \ "wording").as[String], (json \ "comment").as[String])
            case false => IncorrectAnswer((json \ "wording").as[String], (json \ "comment").as[String])
          }
        }
        
      }
        )
      
    
    override def writes(a: Answer): JsValue = JsObject(Seq(
       "wording" -> JsString(a.wording),
       "comment" -> JsString(a.comment),
       a match{
         case b: WeightedAnswer => "weight" -> JsString(b.weight.toString)
         case c: NonWeightedAnswer => "correct" -> JsString(c.correct.toString)
       }
    ))
  }
}
  
  sealed trait NonWeightedAnswer extends Answer{
     def correct: Boolean
  } 
  
  case class WeightedAnswer(wording: String, comment : String, weight: Int)
    extends Answer

    
  case class CorrectAnswer(wording : String, comment : String, correct: Boolean = true) 
    extends NonWeightedAnswer

  
  case class IncorrectAnswer(wording : String, comment : String, correct: Boolean = false)
    extends NonWeightedAnswer
  
  case class Question(title : String, wording: String, options : Seq[Answer])
  
  object JsonFormats{
  
  import play.api.libs.json.Json
  import play.api.data._
  
  

  implicit val correctAnswerFormat = Json.format[CorrectAnswer]
  implicit val incorrectAnswerFormat = Json.format[IncorrectAnswer]
  implicit val questionFormat = Json.format[Question]
  implicit val weightedAnswerFormat = Json.format[WeightedAnswer]
 
}
