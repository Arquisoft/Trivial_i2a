package test.parser.xml

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
import scala.concurrent.duration._
import scala.xml._

class XMLTest extends FlatSpec with Matchers{
  

     "A XML test" should "be correctly read" in {
       val filePath = "src/test/resources/assessment.qti"
       val xml = XML.loadFile(filePath)
       val seqQuestions = Question.fromXML(xml)
       assert(seqQuestions.toString ==="""List(SingleChoiceQuestion(,What does it say?,,List(CorrectAnswer(You must stay with your luggage at all times.,,true), IncorrectAnswer(Do not let someone else look after your luggage.,,false), IncorrectAnswer(Remember your luggage when you leave.,,false))), MultipleChoiceQuestion(,Which of the following elements are used to form water?,,List(WeightedAnswer(Hydrogen,,50), WeightedAnswer(Helium,,-100), WeightedAnswer(Carbon,,-100), WeightedAnswer(Oxygen,,50), WeightedAnswer(Nitrogen,,-100), WeightedAnswer(Chlorine,,-50))))""")
       
       
    }
     
     
     
                                                
                                                
      
      
}