package app

import play.api._
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json._
import scala.concurrent.Future
import scala.util.parsing.combinator._
import parsers._

// Reactive Mongo imports
import reactivemongo.api._

// Reactive Mongo plugin, including the JSON-specialized collection
import play.modules.reactivemongo.MongoController
import play.modules.reactivemongo.json.collection.JSONCollection
import inputLanguages._

object ApplicationJson{
   
    
    import models._
    import models.JsonFormats._
    def main() {

    val filename = io.StdIn.readLine("Please enter the filename where the questions are:")
    val lines = io.Source.fromFile(filename).mkString
    //Now the input language is inferred from the name of the file
    // val inputLanguage = io.StdIn.readLine("Which is the input language?").toLowerCase()
    val FileName = """([^.]*)\.([^.]*)""".r
    val FileName(name, extension) = filename
    val parser: Parser = getLanguageParser(extension)
    val questions = parser.execute(filename)
    saveData(questions)
    }
    
    
  def saveData(questions: Seq[Question]){
    val driver = new MongoDriver
    val connection = driver.connection(List("localhost:27017"))
    val db = connection("trivial")
    val collection: JSONCollection = db.collection[JSONCollection]("questions")
    questions.map { question => { collection.insert(question) } }
    connection.close()
  }
    

  def getLanguageParser(string: String): Parser = string match {
    case "gift" => new GIFTParser()
    case "xml" => new XMLParser()
  }
          
}