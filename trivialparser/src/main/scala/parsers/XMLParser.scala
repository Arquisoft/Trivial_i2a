package parsers

import models.Question

/**
 * @author rubcuevas
 * 
 * XMLParser parses a QTI file
 */
class XMLParser extends Parser{
  
   def execute(filePath: String) : Option[Seq[Question]]= {
     util.Try{xml.XML.loadFile(filePath)} match {
       case util.Success(xmlLines) => Some(Question.fromXML(xmlLines))
       case util.Failure(ex) => {
         System.err.println(ex.getMessage)
         None
       }
     }
     
  }
  
}