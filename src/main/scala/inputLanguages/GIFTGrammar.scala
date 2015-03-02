

/**
 * @author rubcuevas
 */

package   inputLanguages

import scala.util.parsing.combinator._
import org.json4s._
import org.json4s.native.JsonMethods._
import org.json4s.native.Serialization
import reactivemongo.api.MongoDriver
import reactivemongo.bson._
import play.modules.reactivemongo.json.collection._
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.json._
import models._


  trait GIFTGrammar extends InputLanguage with JavaTokenParsers {

  
   def questions: Parser[Seq[Question]] = rep(question) ^^ {_.toSeq}
    def question : Parser[Question] = opt(comment)~>opt(category)~opt(questionTitle)~questionWording~options ^^ 
    {case (None~None~q~o) => o(0) match{
      case a: SingleChoiceAnswer => SingleChoiceQuestion("","", q, o.map { x => x.asInstanceOf[SingleChoiceAnswer] }.toSeq)
      case b: WeightedAnswer => MultipleChoiceQuestion("","", q, o.map { x => x.asInstanceOf[WeightedAnswer] }.toSeq)
      case c: BooleanAnswer => BooleanQuestion("","", q, c)
      case d: MatchingAnswer => MatchingQuestion("","", q, o.map{x => x.asInstanceOf[MatchingAnswer]})
    }
    case (Some(cat)~Some(t)~q~o) => o(0) match{
      case a: SingleChoiceAnswer => SingleChoiceQuestion(cat,t, q, o.map { x => x.asInstanceOf[SingleChoiceAnswer] }.toSeq)
      case b: WeightedAnswer => MultipleChoiceQuestion(cat,t, q, o.map { x => x.asInstanceOf[WeightedAnswer] }.toSeq)
      case c: BooleanAnswer => BooleanQuestion(cat,t, q, c)
      case d: MatchingAnswer => MatchingQuestion(cat,t,q, o.map {x => x.asInstanceOf[MatchingAnswer]})}
    
   // case _ => SingleChoiceQuestion("c", "t", "w", Seq(CorrectAnswer("wording","comment")))
      
    }
    def category: Parser[String] = "$CATEGORY:"~>"""[A-Za-z0-9 ]*""".r ^^(_.toString) 
    def allChars : Parser[String] = "[A-Za-z0-9 ¿?!¡@\"¨'%&$#*+-\\[\\]\\(\\);:,]*".r ^^{_.toString}
   def comment: Parser[String] = allChars ^^ {_.toString}
    def questionTitle : Parser[String] = "::"~>"""[A-Za-z0-9!¡ ]*""".r<~"::"
    def questionWording : Parser[String] = allChars ^^ {_.toString}
    def options : Parser[Seq[Answer]] = "{"~>rep(option)<~"}" ^^{_.toSeq}
    def option : Parser[Answer] = correctAnswer | wrongAnswer | weightedAnswer | booleanAnswer
    def correctAnswer: Parser[CorrectAnswer] = 
      correctAnswerWording~opt(answerComment) ^^
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
      case(bs~None) => if(bs.equals("F") || bs.equals("FALSE")) BooleanAnswer("",true) else BooleanAnswer("", false)
    }
    def matchingAnswer : Parser[MatchingAnswer] = "="~>allChars~("->"~>allChars)~opt(comment) ^^{
      case(a~b~Some(com)) => MatchingAnswer(a,com,b)
      case(a~b~None) => MatchingAnswer(a,"", b)
    }
    def answerWording: Parser[String] = allChars ^^{_.toString}
    def correctAnswerWording: Parser[String] = "="~>answerWording ^^(_.toString)
    def wrongAnswerWording : Parser[String] = "~"~>"""[^%].*""".r ^^(_.toString)
    def answerComment : Parser[String] = "#"~>allChars ^^ (_.toString)
    def percentage : Parser[Int] = "~%"~>"[-]{0,1}[0-9]+".r<~"%" ^^{ _.toInt}
    def trueStatement : Parser[String] = "T\\b".r | "TRUE\\b".r
    def falseStatement : Parser[String] = "F\\b".r | "FALSE\\b".r
    def booleanStatement : Parser[String] = trueStatement | falseStatement
   
}




