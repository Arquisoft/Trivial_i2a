package inputLanguages

import scala.util.parsing.combinator._
import models._

trait InputLanguage extends JavaTokenParsers{
  def questions : Parser[Seq[Question]]
}