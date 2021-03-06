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
class BooleanQuestionParsingTest extends FlatSpec with Matchers{
  
  val parser = new GIFTParser()
  
  "The parser" should "correctly read a Boolean Question " in {
    val question = parser.readFile("src/test/resources/booleanquestion.gift").get(0)
    val shouldBe = BooleanQuestion("category", "title", "Grant was buried in a tomb in New York City.", 
        BooleanAnswer("", true))
    shouldBe should be === question
  }
  "The parser" should "correctly read a Boolean Question without category" in {
    val question = parser.readFile("src/test/resources/booleanquestion.gift").get(1)
    val shouldBe = BooleanQuestion("", "title", "Grant was buried in a tomb in New York City.", 
        BooleanAnswer("", true))
    assert(shouldBe === question)
  }
  "The parser" should "correctly read a Boolean Question with category but with title" in {
    val question = parser.readFile("src/test/resources/booleanquestion.gift").get(2)
    val shouldBe = BooleanQuestion("category", "", "Grant was buried in a tomb in New York City.", 
        BooleanAnswer("", true))
    assert(shouldBe === question)
  }
  "The parser" should "correctly read a Boolean Question without category nor title" in {
    val question = parser.readFile("src/test/resources/booleanquestion.gift").get(3)
    val shouldBe = BooleanQuestion("", "", "Grant was buried in a tomb in New York City.", 
        BooleanAnswer("", true))
    assert(shouldBe === question)
  }
  
}