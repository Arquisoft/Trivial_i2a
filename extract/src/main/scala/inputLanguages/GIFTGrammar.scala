

/**
 * @author rubcuevas
 */

package   inputLanguages

import scala.util.parsing.combinator._
import org.json4s._
import org.json4s.native.JsonMethods._
import org.json4s.native.Serialization
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.json._
import models._

 /**
  * This is the GIFT grammar
  */
  trait GIFTGrammar extends InputLanguage with JavaTokenParsers {

  
   def questions: Parser[Seq[Question]] = rep(question) ^^ {_.toSeq}
    def question : Parser[Question] = opt(comment)~>opt(category)~opt(questionTitle)~questionWording~options ^^ 
    {case (None~None~q~o) => o(0) match{
      case a: SingleChoiceAnswer => SingleChoiceQuestion("","", q, o.map { x => x.asInstanceOf[SingleChoiceAnswer] }.toSeq)
      case b: WeightedAnswer => MultipleChoiceQuestion("","", q, o.map { x => x.asInstanceOf[WeightedAnswer] }.toSeq)
      case c: BooleanAnswer => BooleanQuestion("","", q, c)
      case d: MatchingAnswer => MatchingQuestion("","", q, o.map{x => x.asInstanceOf[MatchingAnswer]}.toSeq)
    }
    case (Some(cat)~Some(t)~q~o) => o(0) match{
      case a: SingleChoiceAnswer => SingleChoiceQuestion(cat,t, q, o.map { x => x.asInstanceOf[SingleChoiceAnswer] }.toSeq)
      case b: WeightedAnswer => MultipleChoiceQuestion(cat,t, q, o.map { x => x.asInstanceOf[WeightedAnswer] }.toSeq)
      case c: BooleanAnswer => BooleanQuestion(cat,t, q, c)
      case d: MatchingAnswer => MatchingQuestion(cat,t,q, o.map {x => x.asInstanceOf[MatchingAnswer]}.toSeq)
      }
    case (Some(cat)~None~q~o) => o(0) match{
      case a: SingleChoiceAnswer => SingleChoiceQuestion(cat,"", q, o.map { x => x.asInstanceOf[SingleChoiceAnswer] }.toSeq)
      case b: WeightedAnswer => MultipleChoiceQuestion(cat,"", q, o.map { x => x.asInstanceOf[WeightedAnswer] }.toSeq)
      case c: BooleanAnswer => BooleanQuestion(cat,"", q, c)
      case d: MatchingAnswer => MatchingQuestion(cat,"",q, o.map {x => x.asInstanceOf[MatchingAnswer]}.toSeq)}
    case (None~Some(t)~q~o) => o(0) match{
      case a: SingleChoiceAnswer => SingleChoiceQuestion("",t, q, o.map { x => x.asInstanceOf[SingleChoiceAnswer] }.toSeq)
      case b: WeightedAnswer => MultipleChoiceQuestion("",t, q, o.map { x => x.asInstanceOf[WeightedAnswer] }.toSeq)
      case c: BooleanAnswer => BooleanQuestion("",t, q, c)
      case d: MatchingAnswer => MatchingQuestion("",t,q, o.map {x => x.asInstanceOf[MatchingAnswer]}.toSeq)}
    
   // case _ => SingleChoiceQuestion("c", "t", "w", Seq(CorrectAnswer("wording","comment")))
      
    }
    def category: Parser[String] = "$CATEGORY:"~>"""[A-Za-z0-9 ]*""".r ^^(_.toString) 
    def allChars : Parser[String] = "[A-Za-z0-9 ¿?!¡@\"¨'%&$#*+-\\[\\]\\(\\);:,]*".r ^^{_.toString}
   def comment: Parser[String] = "//"~allChars ^^ {_.toString}
    def questionTitle : Parser[String] = "::"~>"""[A-Za-z0-9!¡ ]*""".r<~"::"
    def questionWording : Parser[String] = allChars ^^ {_.toString}
    def options : Parser[Seq[Answer]] = "{"~>rep(option)<~"}" ^^{_.toSeq}
    def option : Parser[Answer] = correctAnswer | wrongAnswer | weightedAnswer | booleanAnswer | matchingAnswer
    def correctAnswer: Parser[CorrectAnswer] = 
      "="~>"""\w+""".r~opt(answerComment) ^^
        {case (wording~Some(comment)) => CorrectAnswer(wording, comment)
        case(wording~None) => CorrectAnswer(wording, "")}
    def wrongAnswer: Parser[IncorrectAnswer] = 
        wrongAnswerWording~opt(answerComment) ^^
        {case (wording~None) => IncorrectAnswer(wording, "")
        case(wording~Some(comment)) => IncorrectAnswer(wording, comment)}
    def weightedAnswer : Parser[Answer] = percentage~answerWording~opt(answerComment) ^^{
      case (percentage~wording~None) => WeightedAnswer(wording, "", percentage)
      case(percentage~wording~Some(comment)) => WeightedAnswer(wording, comment, percentage)
    }
    def booleanAnswer : Parser[BooleanAnswer] = (booleanStatement)~opt(answerComment) ^^ {
      case (bs~Some(com)) => if(bs.equals("T") || bs.equals("TRUE")) BooleanAnswer(com,true) else BooleanAnswer(com, false)
      case(bs~None) => if(bs.equals("F") || bs.equals("FALSE")) BooleanAnswer("",false) else BooleanAnswer("", true)
    }
    def matchingAnswer : Parser[MatchingAnswer] = "="~>"""\w+->\w+""".r~opt(comment) ^^{
      case(a~Some(com)) => MatchingAnswer(a.trim(),com,a.trim())
      case(a~None) => MatchingAnswer(a.trim(),"", a.trim())
    }
    def answerWording: Parser[String] = "[A-Za-z0-9'?. ]*".r ^^{_.toString}
    def correctAnswerWording: Parser[String] = "="~>"""[^-].*""".r ^^(_.toString)
    def wrongAnswerWording : Parser[String] = "~"~>"""[^%].*""".r ^^(_.toString)
    def answerComment : Parser[String] = "#"~>allChars ^^ (_.toString)
    def percentage : Parser[Int] = "~%"~>"[-]{0,1}[0-9]+".r<~"%" ^^{ _.toInt}
    def trueStatement : Parser[String] = "T\\b".r | "TRUE\\b".r
    def falseStatement : Parser[String] = "F\\b".r | "FALSE\\b".r
    def booleanStatement : Parser[String] = trueStatement | falseStatement
   
}

object TestGIFT extends GIFTGrammar {
  def main() = {
    val testObj = """
=Canada->Ottawa
"""
    parse(option, testObj)
  }
}




