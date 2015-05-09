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
  case class Config(input: String = "", mode: String = "output", dbname: String = "trivial", 
      collection: String = "questions", output: String = "", verbose: Boolean = false)
    
  val parser = new scopt.OptionParser[Config]("trivialparser") {
    head("trivialparser", "1.x")
    opt[String]('i', "input") required() valueName("<filename>") action { (x, c) =>
      c.copy(input = x) } text("input is the questions filename")  
    opt[String]('o', "output") optional() valueName("<filename>") action {(x, c) =>
      c.copy(output = x)} text("filename is the output file where the questions should be extracted to")
    opt[Unit]('v', "verbose") optional() action {(_, c) =>
      c.copy(verbose = true)} text("print output stream")
     help("help") text("prints the help")
     cmd("insert") action{(_, c) => 
      c.copy(mode = "insert") } text("Insert to database") children(
      
      opt[String]('d', "dbname") optional() valueName("<db>") action { (x, c) => 
        c.copy(dbname = x)} text("db is the name of the output database"),
      opt[String]('c', "collection") optional() valueName("<collection>") action {(x, c) =>
        c.copy(collection = x)} text("collection is the output collection"))
    
    
    
  }
  
  parser.parse(args, Config()) match {
    case Some(config) => {
      Controller.start(config.input, config.mode, config.dbname, config.collection, config.output, config.verbose)
    }
    case None => System.err.println("Could not read the args")
  }
  
  
  
  
  
 
 
 
   
}