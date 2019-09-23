package models

import com.google.inject.Inject
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms._
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.JdbcProfile
import scala.concurrent.{ ExecutionContext, Future }
import slick.jdbc.MySQLProfile.api._
import scala.concurrent.ExecutionContext

case class UserDB(id: Int, service: String, key: String)

class UserDBTableDef(tag: Tag) extends Table[UserDB](tag, "user") {

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def service = column[String]("service")
  def key = column[String]("key")

  override def * =
    (id, service, key) <> ((UserDB.apply _).tupled, UserDB.unapply)
}

class UsersDB @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {

  val usersDB = TableQuery[UserDBTableDef]

  def add(userDB: UserDB): Future[String] = {
    dbConfig.db.run(usersDB += userDB).map(res => "Author successfully added").recover {
      case ex: Exception => ex.getCause.getMessage
    }
  }

  def delete(id: Int): Future[Int] = {
    dbConfig.db.run(usersDB.filter(_.id === id).delete)
  }

  def get(id: Int): Future[Option[UserDB]] = {
    dbConfig.db.run(usersDB.filter(_.id === id).result.headOption)
  }

  def exist(provider: String, userID: String): Future[Option[UserDB]] = {
    dbConfig.db.run(usersDB.filter(_.key === userID).filter(_.service === provider).result.headOption)
  }

  def listAll: Future[Seq[UserDB]] = {
    dbConfig.db.run(usersDB.result)
  }

}

