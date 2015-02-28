package parsers
import models.Question
import scala.util.parsing.combinator.JavaTokenParsers

trait Parser extends JavaTokenParsers{
  def execute(filePath : String) : Seq[Question]
  def questions : Parser[Seq[Question]]
}