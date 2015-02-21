

/**
 * @author rubcuevas
 */

package inputLanguages

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

  class GIFT extends InputLanguage with JavaTokenParsers {

  
   def questions: Parser[Seq[Question]] = rep(question) ^^ {_.toSeq}
    def question : Parser[Question] = opt(questionTitle)~questionWording~options ^^ 
    {case (None~q~o) => Question("", q, o)
    case (Some(title)~q~o) => Question(title, q, o)}
    def questionTitle : Parser[String] = "::"~>"[a-zA-z0-9_ ]*".r<~"::"
    def questionWording : Parser[String] = "[a-zA-z0-9?_ ]*".r ^^ (_.toString)
    def options : Parser[Seq[Answer]] = "{"~>rep(option)<~"}" ^^{_.toSeq}
    def option : Parser[Answer] = correctAnswer | wrongAnswer | weightedAnswer
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
    def answerWording: Parser[String] = "[a-zA-z0-9_ ]*".r
    def correctAnswerWording: Parser[String] = "="~>answerWording ^^(_.toString)
    def wrongAnswerWording : Parser[String] = "~[^%]".r~>answerWording ^^(_.toString)
    def answerComment : Parser[String] = "#"~>"[a-zA-z0-9_ ]*".r ^^ (_.toString)
    def percentage : Parser[Int] = "~%"~>"[-]{0,1}[0-9]+".r<~"%" ^^{ _.toInt}
    
    
    
    
    
    
   
   
    
  
}



object TestGIFT extends GIFT
{
  //implicit val formats = Serialization.formats(NoTypeHints)

        
 def main(){
    val testObj = """What two people are entombed in Grants tomb{
~%-100%No one
~%50%Grant
~%50%Grants wife
#NOOOOOOO
~%100%Grants father
}"""


   
    println(parse(questions, testObj))
  }
}
//       val driver = new MongoDriver
//       val connection = driver.connection(List("localhost"))
//       val db = connection.db("trivial")
//       val collection : JSONCollection = db.collection[JSONCollection]("questions")
//       def main() = {
//        // val json = write(parse(questions, testObj).get)
//         val a1 = CorrectAnswer("pregunta1", "hey")
//         val a2 = IncorrectAnswer("pregunta2", "Comment to pregunta2")
//         val a3 = IncorrectAnswer("pregunta3", "Comment to pregunta3")
//         val opts = List(a1, a2, a3)
//         val prueba = Question("theWording", opts)
         
        
             
          
         
         
//  
//        
//  }
//
//}




