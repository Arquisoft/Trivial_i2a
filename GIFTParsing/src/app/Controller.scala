package app

import play.api._
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json._
import scala.concurrent.Future
import scala.util.parsing.combinator._

// Reactive Mongo imports
import reactivemongo.api._

// Reactive Mongo plugin, including the JSON-specialized collection
import play.modules.reactivemongo.MongoController
import play.modules.reactivemongo.json.collection.JSONCollection
import inputLanguages._

object ApplicationJson {

  import models._
  import models.JsonFormats._
  def main() {

    //val filename = io.StdIn.readLine("Please enter the filename where the questions are:")
    val filename = "files/test.gift"
    val lines = io.Source.fromFile(filename).mkString
    val inputLanguage = io.StdIn.readLine("Which is the input language?").toLowerCase()
    val language : InputLanguage = getLanguage(inputLanguage)
   // val outputLanguage = io.StdIn.readLine("Which language should be questions be translated to?").toLowerCase()
    val outputLanguage = "json";
    outputLanguage match {
      case "json" => {
        val driver = new MongoDriver
        val connection = driver.connection(List("localhost:27017"))
        val db = connection("trivial")
        val collection: JSONCollection = db.collection[JSONCollection]("questions")
        language.parse(language.questions, lines).get.map { question =>
          {

            collection.insert(question)

          }
        }
        connection.close()
      }

    }

  }
  def getLanguage(string: String): InputLanguage = string match {
    case "gift" => new GIFT()
    //case "xml" => new XML()
}

}
