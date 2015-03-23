package parsers

import models.Question

/**
 * @author rubcuevas
 * 
 * XMLParser parses a QTI file
 */
class QTIParser extends Parser{
  
   def readFile(filePath: String): Option[Seq[Question]]= {
     util.Try{io.Source.fromFile(filePath) mkString} match {
       case util.Success(lines) => readString(lines)
       case util.Failure(ex) => {
         System.err.println(ex.getMessage)
         None
       }
     }
     
  }
   
   def readString(string: String) : Option[Seq[Question]] = {
     util.Try{xml.XML.loadString(string)} match {
       case util.Success(xmlLines) => Some(Question.fromXML(xmlLines))
       case util.Failure(ex) => {
         System.err.println(ex.getMessage)
         None
       }
     }
   }
  
}