package test

import org.scalatest._
import parsers._
import models._

class UnitSpec extends FlatSpec with Matchers{
  

  
      "A Parser" should "read a Question from the file" in {
      val parser = new GIFTParser()
      val parsed = parser.execute("files/test.gift")
      println(parsed)
      val shouldReturn = List(SingleChoiceQuestion("","Who is buried in Grants tomb in New York City",List(CorrectAnswer("Grant","Yesss",true), IncorrectAnswer("Ruben","Noooooo",false))))
      assert(parsed === shouldReturn)
      }
}