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
class GIFTTest extends FlatSpec with Matchers{
  
  
  val parser = new GIFTParser()
   
  
  "The parser" should "correctly read this file" in {
    
    val questions = parser.execute("src/test/resources/questions.gift")
  }
  
    "The parser" should "be able to read a SingleChoiceQuestion" in {
      val questions = parser.execute("src/test/resources/singlechoicequestion.gift")
         val shouldBe = SingleChoiceQuestion("category", "title", "Who is buried in Grant's tomb in New York City?", Seq(
             CorrectAnswer("Grant", "Yesss"), IncorrectAnswer("Ruben", "Noooooo")))
         assert(shouldBe === questions.get(0))
        
     }
    
    "The parser" should "be able to read a SingleChoiceQuesiton without category" in {
      val questions = parser.execute("src/test/resources/singlechoicequestion.gift")
         val shouldBe = SingleChoiceQuestion("", "title", "Who is buried in Grant's tomb in New York City?", Seq(
             CorrectAnswer("Grant", "Yesss"), IncorrectAnswer("Ruben", "Noooooo")))
         assert(shouldBe === questions.get(1))
    }
    
    "The parser" should "be able to read a SingleChoiceQuesiton with without title nor category" in {
      val questions = parser.execute("src/test/resources/singlechoicequestion.gift")
         val shouldBe = SingleChoiceQuestion("", "", "Who is buried in Grant's tomb in New York City?", Seq(
             CorrectAnswer("Grant", "Yesss"), IncorrectAnswer("Ruben", "Noooooo")))
         assert(shouldBe === questions.get(2))
    }
    
     "The parser" should "be able to read a SingleChoiceQuesiton with category but without title" in {
      val questions = parser.execute("src/test/resources/singlechoicequestion.gift")
         val shouldBe = SingleChoiceQuestion("category", "", "Who is buried in Grant's tomb in New York City?", Seq(
             CorrectAnswer("Grant", "Yesss"), IncorrectAnswer("Ruben", "Noooooo")))
         assert(shouldBe === questions.get(3))
    }
    
    
  
  "The parser" should "not be able to read a file that does not exist" in {
     val questions = parser.execute("this/path/does/not/exist.gift")
     assert(questions === None)
  }
  
  "The parser" should "correctly read a multiple choice question" in {
     val question = parser.execute("src/test/resources/multiplechoicequestion.gift").get(0)
     val shouldBe = MultipleChoiceQuestion("category", "title", "What two people are entombed in Grants tomb?",
         Seq(WeightedAnswer("No one", "", -100), WeightedAnswer("Grant", "", 50), WeightedAnswer("Grants wife","", 50),
             WeightedAnswer("Grants father", "", -100)))
             
     assert (shouldBe === question)
  }

}