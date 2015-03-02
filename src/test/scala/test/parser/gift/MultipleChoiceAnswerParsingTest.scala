package test.parser.gift

import org.scalatest._
import parsers._
import models._
import scala.concurrent._
import reactivemongo.api._
import reactivemongo.bson._
import play.api.libs.json._
import play.api.data._
import play.modules.reactivemongo.json.BSONFormats._
import play.modules.reactivemongo.json.collection._
import org.json4s._
import org.json4s.native.JsonMethods._
import models.JsonFormats._
import scala.concurrent.duration._
import scala.xml._



class MultipleChoiceAnswerParsingTest extends FlatSpec with Matchers{
  
  val parser = new GIFTParser()
  
  "The parser" should "correctly read a multiple choice question" in {
     val question = parser.execute("src/test/resources/multiplechoicequestion.gift").get(0)
     val shouldBe = MultipleChoiceQuestion("category", "title", "What two people are entombed in Grants tomb?",
         Seq(WeightedAnswer("No one", "", -100), WeightedAnswer("Grant", "", 50), WeightedAnswer("Grants wife","", 50),
             WeightedAnswer("Grants father", "", -100)))
             
     assert (shouldBe === question)
  }
   "The parser" should "correctly read a multiple choice question without category" in {
     val question = parser.execute("src/test/resources/multiplechoicequestion.gift").get(1)
     val shouldBe = MultipleChoiceQuestion("", "title", "What two people are entombed in Grants tomb?",
         Seq(WeightedAnswer("No one", "", -100), WeightedAnswer("Grant", "", 50), WeightedAnswer("Grants wife","", 50),
             WeightedAnswer("Grants father", "", -100)))
             
     assert (shouldBe === question)
  }
   
   "The parser" should "correctly read a multiple choice question with category but without title" in {
     val question = parser.execute("src/test/resources/multiplechoicequestion.gift").get(2)
     val shouldBe = MultipleChoiceQuestion("category", "", "What two people are entombed in Grants tomb?",
         Seq(WeightedAnswer("No one", "", -100), WeightedAnswer("Grant", "", 50), WeightedAnswer("Grants wife","", 50),
             WeightedAnswer("Grants father", "", -100)))
             
     assert (shouldBe === question)
  }
   
   "The parser" should "correctly read a multiple choice question without category nor title" in {
     val question = parser.execute("src/test/resources/multiplechoicequestion.gift").get(3)
     val shouldBe = MultipleChoiceQuestion("", "", "What two people are entombed in Grants tomb?",
         Seq(WeightedAnswer("No one", "", -100), WeightedAnswer("Grant", "", 50), WeightedAnswer("Grants wife","", 50),
             WeightedAnswer("Grants father", "", -100)))
             
     assert (shouldBe === question)
  }

}