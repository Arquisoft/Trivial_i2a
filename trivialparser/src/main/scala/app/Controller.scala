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
import java.nio.file.{Paths, Files}
import java.nio.charset.StandardCharsets


/**
 * Object Controller. This contains the app main method.
 */
object Controller{
   
    
    import models._
   
    def start(questionsFilename: String, mode: String, db: String, collection: String, 
        output: String, verbose: Boolean) {
        getLanguageParser(questionsFilename) match {
          case Some(parser) => {
             parser.readFile(questionsFilename) match {
               case Some(questions) => {
                 if(mode.equals("insert")) saveData(questions, db, collection, verbose)
                 if(!output.isEmpty) writeToFile(questions, output, verbose)
               }
               case None => System.err.println("An error ocurred")
             }
               
          }
          case None => {
            System.err.println("File extension must be either .gift or .qti")
          }
        }
       
        

     
    }
  
  

    
  def saveData(questions: Seq[Question], dbName: String, collectionName: String, verbose: Boolean) = {
    val driver = new MongoDriver
    Try{driver.connection(List("localhost:27017"))} match{
      case Success(connection) => {
        val db = connection(dbName)
        val collection: JSONCollection = db.collection[JSONCollection](collectionName)
 
        questions.map { question => { collection.insert(question)
           .onComplete { 
             case Failure(e) => System.err.println(e.getMessage) 
             case Success(lastError) => {
               if(verbose)
                 System.out.println(
                   "Questions have been correctly inserted in database " + dbName + "and collection " + collectionName)}
             }} 
        
      }
        
       }
      case Failure(ex) => System.err.println("Could not connect to the database: " )
    }
   
    
   
  }
  
  def writeToFile(questions: Seq[Question], outputFilename: String, verbose: Boolean) = {
    val qj = questions.map { x => Json.toJson(x).toString }
    val txt = qj.reduceLeft(_ + "\n" + _)
    if(verbose) println("txt")
    Files.write(Paths.get(outputFilename), txt.getBytes(StandardCharsets.UTF_8))
  }
    

  def getLanguageParser(filename: String): Option[Parser] = {
    if(filename.endsWith(".gift"))
      Some(new GIFTParser())
    else if(filename.endsWith(".qti"))
      Some(new QTIParser())
    else None
  }
          
}