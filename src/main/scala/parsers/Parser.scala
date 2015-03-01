package parsers
import models.Question
import scala.util.parsing.combinator.JavaTokenParsers


trait Parser {

  def execute(filePath : String) : Seq[Question]
  
}