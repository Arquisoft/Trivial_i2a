package test
import org.scalatest._
import parsers._
import models._
import scala.concurrent._
import reactivemongo.api._
import reactivemongo.bson._
import play.api.libs.json._
import play.api.libs.json.Json
import play.api.data._
import play.modules.reactivemongo.json.BSONFormats._
import play.modules.reactivemongo.json.collection._
import org.json4s._
import org.json4s.native.JsonMethods._
import models.JsonFormats._
import scala.concurrent.duration._
import scala.math.BigDecimal.int2bigDecimal
import scala.concurrent.ExecutionContext.Implicits.global
import scala.xml._
/**
 * @author rubcuevas
 */
class AnswerValidation extends FlatSpec with Matchers{
       "A SingleChoiceAnswer" should "be validated" in {
      
       val json = JsObject(Seq("wording" -> JsString("the wording"),
           "comment" -> JsString(""), "correct" -> JsBoolean(true)))
       val sca = Json.fromJson[Answer](json).get
      // val b = CorrectAnswer("the wording", "")
    
       assert(sca === CorrectAnswer("the wording", ""))
     }
     
     "A WeightedAnswer" should "be validated" in {
     
       val json = JsObject(Seq("wording" -> JsString("the wording"),
       "comment" -> JsString(""), "weight" -> JsNumber(100)))
       val sca = Json.fromJson[Answer](json).get
      // val b = CorrectAnswer("the wording", "")
       assert(sca === WeightedAnswer("the wording", "", 100))
     }
     
     "A BooleanAnswer" should "be validated" in {
          val json = JsObject(Seq(
       "comment" -> JsString(""), "answer" -> JsBoolean(true)))
       val sca = Json.fromJson[Answer](json).get
      // val b = CorrectAnswer("the wording", "")
       assert(sca === BooleanAnswer("", true))
     }
     
     "A MatchingAnswer" should "be validated" in {
       val json = JsObject(Seq("wording" -> JsString("the wording"),
           "comment" -> JsString(""), "matching" -> JsString("the matching")))
       val ma = Json.fromJson[Answer](json).get
       assert(ma === MatchingAnswer("the wording", "", "the matching"))
     }
     
     
     "A SingleChoiceQuestion" should "be validated" in {
       val ca = CorrectAnswer("Correct", "")
       val ia = IncorrectAnswer("Incorrect", "")
       val scq = SingleChoiceQuestion("","The title", "The wording", Seq(ca, ia))
       val json = JsObject(Seq("category" -> JsString(""),"title" -> JsString("The title"),
           "wording" -> JsString("The wording"), 
           "options" -> JsArray(Seq(Json.toJson(ca),Json.toJson(ia)))))
       assert(scq === Json.fromJson[Question](json).get)
      
     }
}