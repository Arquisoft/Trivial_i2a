package app

import play.api._
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json._
import scala.concurrent.Future
import scala.util.parsing.combinator._
import parsers.GIFTParser

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
    val parser: GIFTParser = getLanguageParser(extension)
    val outputLanguage = io.StdIn.readLine("Which language should be questions be translated to?").toLowerCase()
    
    outputLanguage match {
      case "json" => {
        //If we add new output languages, the connection can be extracted
        val driver = new MongoDriver
        val connection = driver.connection(List("localhost:27017"))
        val db = connection("trivial")
        
        val collection: JSONCollection = db.collection[JSONCollection]("questions")
        
        parser.parse(parser.questions, lines).get.map { question => { collection.insert(question) } }
        connection.close()
      }
    }

  }
  def getLanguageParser(string: String): GIFTParser = string match {
    case "gift" => new GIFTParser()
    //case "xml" => new XML()
  }
          
}