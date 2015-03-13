package run

import app.Controller

import java.io.File




 /*
   * Main method
   * 
   * param: args
   * First argument: Questions filename
   */
object Main extends App {
  case class Config(input: String = "", dbname: String = "trivial", collection: String = "questions")
    
  val parser = new scopt.OptionParser[Config]("trivialparser") {
    head("trivialparser", "1.x")
    opt[String]('i', "input") required() valueName("<filename>") action { (x, c) =>
      c.copy(input = x) } text("input is the questions filename")
    note("The file must be in GIFT or QTI format\n")
    note("The filename must end with .gift or .qti")
    opt[String]('d', "dbname") optional() valueName("<db>") action { (x, c) => 
      c.copy(dbname = x)} text("db is the name of the output database")
    opt[String]('c', "collection") optional() valueName("<collection>") action {(x, c) =>
      c.copy(collection = x)} text("collection is the output collection")
   
    help("help") text("prints the help")
    
    
  }
  
  parser.parse(args, Config()) match {
    case Some(config) => {
      Controller.start(config.input, config.dbname, config.collection)
    }
    case None => System.err.println("Could not read the args")
  }
  
  
  
  
  
 
 
 
   
}