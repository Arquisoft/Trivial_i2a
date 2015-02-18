package controllers

import play.api._
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json._
import scala.concurrent.Future

// Reactive Mongo imports
import reactivemongo.api._

// Reactive Mongo plugin, including the JSON-specialized collection
import play.modules.reactivemongo.MongoController
import play.modules.reactivemongo.json.collection.JSONCollection


object ApplicationJson{
   
    
    import models._
    import models.JsonFormats._
     def main(){
        val driver = new MongoDriver
  val connection = driver.connection(List("localhost:27017"))
      
    val db = connection("trivial")
      val collection: JSONCollection = db.collection[JSONCollection]("questions")
         val cAnswer = CorrectAnswer("Hola", "chao")
         val cAnswer2 = CorrectAnswer("Eseee", "Adiooos")
          
         val question = Question("This is the question", Seq(cAnswer, cAnswer2))
        collection.insert(question)
  }
 
  
  

}