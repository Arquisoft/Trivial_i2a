package test.parser.gift


import org.scalatest._
import parsers._
import models._
import scala.concurrent._
import play.api.libs.json._
import play.api.data._
import org.json4s._
import org.json4s.native.JsonMethods._
import scala.concurrent.duration._
import scala.xml._
/**
 * @author rubcuevas
 */
class GIFTTest extends FlatSpec with Matchers{
  
  
  val parser = new GIFTParser()
   
  
  "The parser" should "correctly read this file" in {
    
    val questions = parser.readFile("src/test/resources/questions.gift")
  }
  
    "The parser" should "be able to read a SingleChoiceQuestion" in {
      val questions = parser.readFile("src/test/resources/singlechoicequestion.gift")
         val shouldBe = SingleChoiceQuestion("category", "title", "Who is buried in Grant's tomb in New York City?", Seq(
             CorrectAnswer("Grant", "Yesss"), IncorrectAnswer("Ruben", "Noooooo")))
         assert(shouldBe === questions.get(0))
        
     }
    
    "The parser" should "be able to read a SingleChoiceQuesiton without category" in {
      val questions = parser.readFile("src/test/resources/singlechoicequestion.gift")
         val shouldBe = SingleChoiceQuestion("", "title", "Who is buried in Grant's tomb in New York City?", Seq(
             CorrectAnswer("Grant", "Yesss"), IncorrectAnswer("Ruben", "Noooooo")))
         assert(shouldBe === questions.get(1))
    }
    
    "The parser" should "be able to read a SingleChoiceQuesiton with without title nor category" in {
      val questions = parser.readFile("src/test/resources/singlechoicequestion.gift")
         val shouldBe = SingleChoiceQuestion("", "", "Who is buried in Grant's tomb in New York City?", Seq(
             CorrectAnswer("Grant", "Yesss"), IncorrectAnswer("Ruben", "Noooooo")))
         assert(shouldBe === questions.get(2))
    }
    
     "The parser" should "be able to read a SingleChoiceQuesiton with category but without title" in {
      val questions = parser.readFile("src/test/resources/singlechoicequestion.gift")
         val shouldBe = SingleChoiceQuestion("category", "", "Who is buried in Grant's tomb in New York City?", Seq(
             CorrectAnswer("Grant", "Yesss"), IncorrectAnswer("Ruben", "Noooooo")))
         assert(shouldBe === questions.get(3))
    }
    
    
  
  "The parser" should "not be able to read a file that does not exist" in {
     val questions = parser.readFile("this/path/does/not/exist.gift")
     assert(questions === None)
  }
  
  

}