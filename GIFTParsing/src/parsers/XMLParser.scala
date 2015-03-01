package parsers

import models.Question

/**
 * @author rubcuevas
 */
class XMLParser extends Parser{
   def execute(filePath: String) = {
     val lines = io.Source.fromFile(filePath).mkString
     Question.fromXML(xml.XML.loadFile(lines))
  }
  
}