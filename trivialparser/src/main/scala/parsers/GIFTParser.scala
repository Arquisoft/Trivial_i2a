package parsers
import inputLanguages.GIFTGrammar
import scala.util.parsing.combinator.JavaTokenParsers
import models.Question

/**
 * GIFTParser parses a GIFT file
 */
class GIFTParser extends Parser with JavaTokenParsers with GIFTGrammar{
  
  def execute(filePath: String) : Option[Seq[Question]]= {
   util.Try{io.Source.fromFile(filePath).mkString} match {
      case util.Success(lines) => parse(questions, lines) match {
        case Success(result, next) => Some(result)
        case ns: NoSuccess => {
          System.err.println("Parse could not parse de file. " + ns.msg)
          None
        }
      }
      case util.Failure(ex) => {
        System.err.println(ex.getMessage)
        None
        
      }
    }
     
     
  }

}