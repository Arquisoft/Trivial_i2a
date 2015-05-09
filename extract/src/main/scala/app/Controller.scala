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



import inputLanguages._
import java.nio.file.{Paths, Files}
import java.nio.charset.StandardCharsets
import akka._
import scala.concurrent.duration._
// Imports core, which grabs everything including Query DSL
import com.mongodb.casbah.Imports._
import com.mongodb.util.JSON



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
                 if(mode.equals("insert")) saveData(questions, db, collection)
                 if(!output.isEmpty) writeToFile(questions, output)
                 if(verbose) questions.foreach { x => println(Json.prettyPrint(Json.toJson(x))) }
                
               }
               case None => System.err.println("An error ocurred")
             }
               
          }
          case None => {
            System.err.println("File extension must be either .gift or .qti")
          }
        }
       
        

     
    }
  
  

    
  def saveData(questions: Seq[Question], dbName: String, collectionName: String) = {
    
   val mongoClient = MongoClient("localhost", 27017)
   val db = mongoClient(dbName)
   val coll = db(collectionName)
 
   questions.foreach { x => {
     val obj: JsValue = Json.toJson(x)
     val doc: DBObject = JSON.parse(obj.toString).asInstanceOf[DBObject]
     coll.insert(doc)
   } }
   mongoClient.close
   
  }
  
  def writeToFile(questions: Seq[Question], outputFilename: String) = {
    val qj = questions.map { x => Json.prettyPrint(Json.toJson(x)) }
    val txt = qj.reduceLeft(_ + "\n" + _)
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