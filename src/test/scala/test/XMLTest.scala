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

class XMLTest extends FlatSpec with Matchers{
  
      
  
   
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
     

     
     
     
     
     "A XML test" should "be correctly read" in {
       val filePath = "src/test/resources/assessment.xml"
       val xml = XML.loadFile(filePath)
       val seqQuestions = Question.fromXML(xml)
       assert(seqQuestions.toString ==="""List(SingleChoiceQuestion(,What does it say?,,List(CorrectAnswer(You must stay with your luggage at all times.,,true), IncorrectAnswer(Do not let someone else look after your luggage.,,false), IncorrectAnswer(Remember your luggage when you leave.,,false))), MultipleChoiceQuestion(,Which of the following elements are used to form water?,,List(WeightedAnswer(Hydrogen,,50), WeightedAnswer(Helium,,-100), WeightedAnswer(Carbon,,-100), WeightedAnswer(Oxygen,,50), WeightedAnswer(Nitrogen,,-100), WeightedAnswer(Chlorine,,-50))))""")
    }
     
     
     
                                                
                                                
      
      
}