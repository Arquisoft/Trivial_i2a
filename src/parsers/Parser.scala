package parsers
import models.Question

trait Parser {
  def execute(filePath : String) : Seq[Question]
}