package app

import reactivemongo.api._
import play.modules.reactivemongo._
import play.modules.reactivemongo.json.collection.JSONCollection
import play.api.Play.current
import play.api.libs.json._
import scala.concurrent.ExecutionContext.Implicits._

trait MongoDAO{
  
    /** Name of the MongoDB Collection. */
  protected def colName: String

  /** Returns the default database. */
  protected def db = ReactiveMongoPlugin.db

  /** The actual collection used by the DAO. */
  protected def col: JSONCollection =
    db.collection[JSONCollection](colName)
   
      /** Returns the first result, if any, for the given query. */
  def findOne[DOC](q: JsObject)(implicit reads: Reads[DOC]) =
    col.find(q).cursor[DOC].headOption

}