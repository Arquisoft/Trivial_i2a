/*package inputLanguages


import com.thoughtworks.xstream._
import parsers._
import scala.util.parsing.combinator._
import models._

object XMLUtil {
  private val xstream = new XStream

  def fromXML[T](xml: String): T = {
    xstream.fromXML(xml).asInstanceOf[T]
  } 

  def toXML[T](obj: T): String = {
    xstream.toXML(obj)
    
  } 
}

trait XMLGrammar extends Parser {
  def questions: Parser[Seq[Question]] = rep(Question.fromXML(lines)) ^^ {_.toSeq}
}*/