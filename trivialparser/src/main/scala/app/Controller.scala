package app

import play.api._
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json._

    import play.api.libs.functional.syntax._

import scala.util.parsing.combinator._
import parsers._
import scala.util.{Try, Success, Failure}
import scala.concurrent._

// Reactive Mongo imports
import reactivemongo.api._
import reactivemongo.bson._
// Reactive Mongo plugin, including the JSON-specialized collection
import play.modules.reactivemongo.MongoController
import play.modules.reactivemongo.json.collection.JSONCollection
import inputLanguages._

/**
 * Object Controller. This contains the app main method.
 */
object Controller{
   
    
    import models._
    
    def main() {
      
      Try{io.StdIn.readLine("\nPlease enter the filename where the questions are: ")} match{
      
          case Success(name) => {
            getLanguageParser(name) match {
              case Some(parser) => {
                 parser.execute(name) match {
                   case Some(questions) => saveData(questions)
                   case None => main
                 }
                   
              }
              case None => {
                System.err.println("File extension must be either .gift or .xml")
                main
              }
            }}
          case Failure(ex) => {
             println("Something was wrong: " + ex.getMessage)
             main
          }
        }

     
    }
  
  

    
  def saveData(questions: Seq[Question]) = {
    val driver = new MongoDriver
    Try{driver.connection(List("localhost:27017"))} match{
      case Success(connection) => {
        val db = connection("trivial")
        val collection: JSONCollection = db.collection[JSONCollection]("questions")
 
        questions.map { question => { collection.insert(question)
           .onComplete { 
             case Failure(e) => System.err.println(e.getMessage) 
             case Success(lastError) => {}
             }} 
        
      }
        
       }
      case Failure(ex) => System.err.println("Could not connect to the database: " )
    }
   
    
   
  }
    

  def getLanguageParser(filename: String): Option[Parser] = {
    if(filename.endsWith(".gift"))
      Some(new GIFTParser())
    else if(filename.endsWith(".xml"))
      Some(new XMLParser())
    else None
  }
          
}