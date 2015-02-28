package parsers
import scala.xml.XML
import inputLanguages._

class XMLParser extends Parser with XMLGrammar{
  def execute(filePath: String) = {
     //val lines = io.Source.fromFile(filePath).mkString
     //parse(questions, lines).get
     val lines = XML.loadFile(filePath).mkString
     parse(questions, lines).get
  }
  

}