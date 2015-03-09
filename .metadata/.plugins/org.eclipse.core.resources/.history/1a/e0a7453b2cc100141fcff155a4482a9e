package test.validation

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
import scala.xml._

/**
 * @author rubcuevas
 */

class QuestionValidationTest extends FlatSpec with Matchers{
  
   "The GIFTParser" should "correctly parse a single choice question in GIFT format" in {
      val parser = new GIFTParser()
      val parsed = parser.execute("src/test/resources/test.gift").get
      val scq = List(
          SingleChoiceQuestion("category","title","Who is buried in Grant's tomb in New York City?",
          List(CorrectAnswer("Grant","Yesss"), IncorrectAnswer("Ruben","Noooooo"))))
      assert(parsed === scq)
      }
   
   "A MultipleChoiceQuestion" should "be validated" in {
       val wa = WeightedAnswer("Wording1", "", 50)
       val wa1 = WeightedAnswer("Wording2", "", 50)
       val wa2 = WeightedAnswer("Wording2", "", 100)
       val mcq = MultipleChoiceQuestion("","QuestionTitle", "QuestionWording", Seq(wa,wa1,wa2))
       val json = JsObject(Seq("category" -> JsString(""),"title" -> JsString("QuestionTitle"),
           "wording" -> JsString("QuestionWording"),
           "options" -> JsArray(Seq(Json.toJson(wa), Json.toJson(wa1), Json.toJson(wa2)))))
      val obj = Json.fromJson[Question](json) 
      assert(mcq === Json.fromJson[Question](json).get)
     
     
     }
     
     "A BooleanQuestion" should "be validate" in {
          val ba = BooleanAnswer("", true)
          val bq = BooleanQuestion("","QuestionTitle", "QuestionWording", ba)
          val json = JsObject(Seq("category" -> JsString(""),"title" -> JsString("QuestionTitle"),
              "wording" -> JsString("QuestionWording"), "answer" -> Json.toJson(ba)))
          assert(bq === Json.fromJson[Question](json).get)
     }
     
     "A SingleChoiceQuestion" should "be XML deserialized" in {
       val filePath = "src/test/resources/choice.xml"
       val xml = XML.loadFile(filePath)
       val scq = Question.readSimpleQuestion(xml)
       println(scq)
       assert(scq.toString === "SingleChoiceQuestion(,What does it say?,,List(CorrectAnswer(You must stay with your luggage at all times.,,true), IncorrectAnswer(Do not let someone else look after your luggage.,,false), IncorrectAnswer(Remember your luggage when you leave.,,false)))")
       
     }
     
     "A MultipleChoiceQuestion" should "be XML deserialized" in {
        val filePath = "src/test/resources/choice_multiple.xml"
       val xml = XML.loadFile(filePath)
       val mcq = Question.readMultipleChoiceQuestion(xml)
       println(mcq)
       assert(mcq.toString === "MultipleChoiceQuestion(,Which of the following elements are used to form water?,,List(WeightedAnswer(Hydrogen,,50), WeightedAnswer(Helium,,-100), WeightedAnswer(Carbon,,-100), WeightedAnswer(Oxygen,,50), WeightedAnswer(Nitrogen,,-100), WeightedAnswer(Chlorine,,-50)))")
       
     }
     "A MatchingQuestion" should "be correctly deserialized" in {
       val ma1 = MatchingAnswer("wording1", "", "matching1")
       val ma2 = MatchingAnswer("wording2", "", "matching2")
       val qa = MatchingQuestion("","", "questionWording", Seq(ma1, ma2))
       val json = JsObject(Seq("category" -> JsString(""),"title" -> JsString(""),
           "wording" -> JsString("questionWording"),
           "options" -> JsArray(Seq(Json.toJson(ma1), Json.toJson(ma2)))))
       assert(qa === Json.fromJson[Question](json).get)
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