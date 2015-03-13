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
  def readFile(filePath : String) : Option[Seq[Question]]
  
  def readString(string: String) : Option[Seq[Question]]

  
}