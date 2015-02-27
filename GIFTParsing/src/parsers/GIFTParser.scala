package parsers
import inputLanguages.GIFTGrammar

class GIFTParser extends Parser with GIFTGrammar{
  
  def execute(filePath: String) = {
     val lines = io.Source.fromFile(filePath).mkString
     parse(questions, lines).get
  }

}