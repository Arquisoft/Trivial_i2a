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
/*
/**
 * @author rubcuevas
 */

class MatchingQuestionParsingTest extends FlatSpec with Matchers {
  
  val parser = new GIFTParser()
  
  "The parser" should "correctly read a Matching Question" in {
    val question = parser.execute("src/test/resources/matchingquestion.gift").get(0)
    val shouldBe = MatchingQuestion("category", "title", "Match the following countries with their corresponding capitals.",
        Seq(MatchingAnswer("Canada", "", "Ottawa"), MatchingAnswer("Italy", "", "Rome"),
            MatchingAnswer("Japan", "", "Tokyo"), MatchingAnswer("India", "","New Delhi")))
    assert(shouldBe === question)
  }
  
}
*/