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
class UnitSpec extends FlatSpec with Matchers{
  
      
  
    "The GIFTParser" should "correctly parse a single choice question in GIFT format" in {
      val parser = new GIFTParser()
      val parsed = parser.execute("src/test/resources/test.gift")
      val scq = List(
          SingleChoiceQuestion("","Who is buried in Grant's tomb in New York City?",
          List(CorrectAnswer("Grant","Yesss"), IncorrectAnswer("Ruben","Noooooo"))))
      assert(parsed === scq)
      }
      /*
     "The GIFTParser" should "correctly read a SingleChoiceQuestion from MongoDB" in {
        val driver = new MongoDriver
        val connection = driver.connection(List("localhost:27017"))
        val db = connection("trivial")
        val collection: JSONCollection = db.collection[JSONCollection]("questions")
        val query = BSONDocument(
            "wording" -> "Who is buried in Grants tomb in New York City")
        val q = Await.result(collection.find(query).one[JsValue], 50 seconds)
        q match{case Some(item) => {
          //val obj = Json.fromJson[SingleChoiceQuestion](item)
          println(item)
        }}
        
     }*/
     
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
       val scq = SingleChoiceQuestion("The title", "The wording", Seq(ca, ia))
       val json = JsObject(Seq("title" -> JsString("The title"),
           "wording" -> JsString("The wording"), 
           "options" -> JsArray(Seq(Json.toJson(ca),Json.toJson(ia)))))
       assert(scq === Json.fromJson[Question](json).get)
      
     }
     
     "A MultipleChoiceQuestion" should "be validated" in {
       val wa = WeightedAnswer("Wording1", "", 50)
       val wa1 = WeightedAnswer("Wording2", "", 50)
       val wa2 = WeightedAnswer("Wording2", "", 100)
       val mcq = MultipleChoiceQuestion("QuestionTitle", "QuestionWording", Seq(wa,wa1,wa2))
       val json = JsObject(Seq("title" -> JsString("QuestionTitle"),
           "wording" -> JsString("QuestionWording"),
           "options" -> JsArray(Seq(Json.toJson(wa), Json.toJson(wa1), Json.toJson(wa2)))))
      val obj = Json.fromJson[Question](json) 
      assert(mcq === Json.fromJson[Question](json).get)
     
     
     }
     
     "A BooleanQuestion" should "be validate" in {
          val ba = BooleanAnswer("", true)
          val bq = BooleanQuestion("QuestionTitle", "QuestionWording", ba)
          val json = JsObject(Seq("title" -> JsString("QuestionTitle"),
              "wording" -> JsString("QuestionWording"), "answer" -> Json.toJson(ba)))
          assert(bq === Json.fromJson[Question](json).get)
     }
     
     "A SingleChoiceQuestion" should "be XML deserialized" in {
       val filePath = "src/test/resources/choice.xml"
       val xml = XML.loadFile(filePath)
       val scq = Question.readSimpleQuestion(xml)
       println(scq)
       assert(scq.toString === "SingleChoiceQuestion(What does it say?,,List(CorrectAnswer(You must stay with your luggage at all times.,,true), IncorrectAnswer(Do not let someone else look after your luggage.,,false), IncorrectAnswer(Remember your luggage when you leave.,,false)))")
       
     }
     
     "A MultipleChoiceQuestion" should "be XML deserialized" in {
        val filePath = "src/test/resources/choice_multiple.xml"
       val xml = XML.loadFile(filePath)
       val mcq = Question.readMultipleChoiceQuestion(xml)
       println(mcq)
       assert(mcq.toString === "MultipleChoiceQuestion(Which of the following elements are used to form water?,,List(WeightedAnswer(Hydrogen,,50), WeightedAnswer(Helium,,-100), WeightedAnswer(Carbon,,-100), WeightedAnswer(Oxygen,,50), WeightedAnswer(Nitrogen,,-100), WeightedAnswer(Chlorine,,-50)))")
       
     }
     "A MatchingQuestion" should "be correctly deserialized" in {
       val ma1 = MatchingAnswer("wording1", "", "matching1")
       val ma2 = MatchingAnswer("wording2", "", "matching2")
       val qa = MatchingQuestion("", "questionWording", Seq(ma1, ma2))
       val json = JsObject(Seq("title" -> JsString(""),
           "wording" -> JsString("questionWording"),
           "options" -> JsArray(Seq(Json.toJson(ma1), Json.toJson(ma2)))))
       assert(qa === Json.fromJson[Question](json).get)
     }
     
     
     "A XML test" should "be correctly read" in {
       val filePath = "src/test/resources/assessment.xml"
       val xml = XML.loadFile(filePath)
       val seqQuestions = Question.fromXML(xml)
       assert(seqQuestions.toString ==="""List(SingleChoiceQuestion(What does it say?,,List(CorrectAnswer(You must stay with your luggage at all times.,,true), IncorrectAnswer(Do not let someone else look after your luggage.,,false), IncorrectAnswer(Remember your luggage when you leave.,,false))), MultipleChoiceQuestion(Which of the following elements are used to form water?,,List(WeightedAnswer(Hydrogen,,50), WeightedAnswer(Helium,,-100), WeightedAnswer(Carbon,,-100), WeightedAnswer(Oxygen,,50), WeightedAnswer(Nitrogen,,-100), WeightedAnswer(Chlorine,,-50))))""")
    }
     
     
     
                                                
                                                
      
      
}