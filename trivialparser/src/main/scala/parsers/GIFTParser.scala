package parsers
import inputLanguages.GIFTGrammar
import scala.util.parsing.combinator.JavaTokenParsers
import models.Question

/**
 * GIFTParser parses a GIFT file
 */
class GIFTParser extends Parser with JavaTokenParsers with GIFTGrammar{
  
  def readFile(filePath: String): Option[Seq[Question]]= {
   util.Try{io.Source.fromFile(filePath).mkString} match {
      case util.Success(lines) => readString(lines)
      case util.Failure(ex) => {
        System.err.println(ex.getMessage)
        None
        
      }
    }
     
     
  }
  
  def readString(string: String) : Option[Seq[Question]] = {
     parse(questions, string) match {
        case Success(result, next) => Some(result)
        case ns: NoSuccess => {
          System.err.println("Could not read the questions. " + ns.msg)
          None
        }
      }
  }
}