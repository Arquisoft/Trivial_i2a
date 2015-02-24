package parsers
import inputLanguages.GIFT

class GIFTParser extends Parser with GIFT{
  
  def execute(filePath: String) = {
     val lines = io.Source.fromFile(filePath).mkString
     parse(questions, lines).get
  }

}