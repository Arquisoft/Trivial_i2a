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
  def comment: String
  
		
}

object JsonAnswerFormats{
     implicit val weightedAnswerFormat = Json.format[WeightedAnswer]
  implicit val weightedAnswerReads = Json.reads[WeightedAnswer]
  implicit val booleanAnswerFormat = Json.format[BooleanAnswer]
  implicit val booleanAnswer = Json.format[MatchingAnswer]
}


  object Answer{
  

  implicit object answerFormat extends Format[Answer]{
    
    override def reads(json: JsValue) = JsSuccess(
      (json \ "correct") match {
        case _ : JsUndefined => (json \ "answer") match{
          case _ : JsUndefined => (json \ "matching") match {
            case _ :JsUndefined => WeightedAnswer((json \ "wording").as[String],
                (json \ "comment").as[String], (json \ "weight").as[Int])
            case _ : JsValue => MatchingAnswer((json \ "wording").as[String],
                (json \ "comment").as[String], (json \ "matching").as[String])
          }
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
         case e : MatchingAnswer => Seq("wording" -> JsString(e.wording), "matching" -> JsString(e.matching), "comment" -> JsString(e.comment))
       }
    )
  }
  
      
  }
    
 sealed trait NonWeightedAnswer extends Answer
   
  object NonWeightedAnswer{
    implicit object nonWeightedAnswer extends Format[NonWeightedAnswer]{
      override def reads(json: JsValue) = JsSuccess({
        (json \ "correct") match{
          case _ : JsUndefined => (json \ "matching") match {
            case _ : JsUndefined => BooleanAnswer((json \ "comment").as[String], (json \ "answer").as[Boolean])
            case _ : JsValue => MatchingAnswer((json \ "wording").as[String], 
                (json \ "comment").as[String], (json \ "matching").as[String])
          }
          
          case b: JsValue => {
          (json \ "correct").as[Boolean] match{
            case true => CorrectAnswer((json \ "wording").as[String], (json \ "comment").as[String])
            case false => IncorrectAnswer((json \ "wording").as[String], (json \ "comment").as[String])
          }
        }
        }
      })
    
    
    override def writes(a: NonWeightedAnswer): JsValue = JsObject(
     
       //"wording" -> JsString(a.wording),
        a match {
          case ma : MatchingAnswer => Seq("wording" -> JsString(ma.wording),
              "matching" -> JsString(ma.matching),
              "comment" -> JsString(ma.comment))
          case c: SingleChoiceAnswer => Seq("correct" -> JsBoolean(c.correct), "comment" -> JsString(c.comment))
         case d: BooleanAnswer => Seq("answer" -> JsBoolean(d.answer), "comment" -> JsString(d.comment))
         
      
        }
     
    )
    }
  }
  
  case class WeightedAnswer(wording: String, comment : String, weight: Int)
    extends Answer
    

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
  
  case class MatchingAnswer(wording: String, comment:String, matching: String) extends NonWeightedAnswer
  
  case class BooleanAnswer(comment: String, answer: Boolean) extends NonWeightedAnswer

    
  case class CorrectAnswer(wording : String, comment : String, correct: Boolean = true) 
    extends SingleChoiceAnswer 

  
  case class IncorrectAnswer(wording : String, comment : String, correct: Boolean = false)
    extends SingleChoiceAnswer