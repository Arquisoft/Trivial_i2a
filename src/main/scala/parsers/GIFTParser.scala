package parsers
import inputLanguages.GIFTGrammar
import scala.util.parsing.combinator.JavaTokenParsers

class GIFTParser extends Parser with JavaTokenParsers with GIFTGrammar{
  
  def execute(filePath: String) = {
     val lines = io.Source.fromFile(filePath).mkString
     parse(questions, lines).get
  }

}