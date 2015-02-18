

/**
 * @author rubcuevas
 */

package parsedLanguages

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

  class GIFT extends JavaTokenParsers {


  
    def correctAnswerWording: Parser[String] = "="~>"[a-zA-z0-9_ ]*".r ^^(_.toString)
    def wrongAnswerWording : Parser[String] = "~"~>"[a-zA-z0-9_ ]*".r ^^(_.toString)
    def answerComment : Parser[String] = "#"~>"[a-zA-z0-9_ ]*".r ^^ (_.toString)
    def correctAnswer: Parser[CorrectAnswer] = 
      correctAnswerWording~answerComment ^^
        {case (wording~comment) => CorrectAnswer(wording, comment)}
    def wrongAnswer: Parser[IncorrectAnswer] = 
        wrongAnswerWording~answerComment ^^
        {case (wording~comment) => IncorrectAnswer(wording, comment)}
    def option : Parser[Answer] = correctAnswer | wrongAnswer
    def questionWording : Parser[String] = "::"~>"[a-zA-z0-9?_ ]*".r ^^ (_.toString)
    def options : Parser[Seq[Answer]] = "{"~>rep(option)<~"}" ^^{_.toSeq}
    def question : Parser[Question] = questionWording~options ^^ 
    {case (q~o) => Question(q, o)}
   
    def questions: Parser[Seq[Question]] = rep(question) ^^ {_.toSeq}
}



//object TestGIFT extends GIFT
//{
//  //implicit val formats = Serialization.formats(NoTypeHints)
//
//        
// def main(){
//           val testObj : String = """::Who is buried in Grants tomb in New York City{
//=Grant
//#Yesss
//~Ruben
//#Noooooo
//}
//::This is another question{
//~This is not the answer
//#This is a comment to the wrong answer
//=This is the right answer
//#YESSSSSS
//}"""
//    println(parse(questions, testObj))
//  }
//}
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




