package inputLanguages


import com.thoughtworks.xstream._

object XMLUtil {
  private val xstream = new XStream

  def fromXML[T](xml: String): T = {
    xstream.fromXML(xml).asInstanceOf[T]
  } 

  def toXML[T](obj: T): String = {
    xstream.toXML(obj)
    
  } 
}