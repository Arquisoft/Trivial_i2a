package parsers
import models.Question
import scala.util.parsing.combinator.JavaTokenParsers

/**
 * A Parser is the entity that will parse the input file
 */
trait Parser {
  
  /**
   * Read a file an return the list of questions
   */
  def execute(filePath : String) : Option[Seq[Question]]
  

  
}