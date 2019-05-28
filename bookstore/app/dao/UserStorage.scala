package dao

import javax.inject.Inject
import models.User
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class UserStorage @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {


  class Users(tag: Tag) extends Table[User](tag, "users") {

    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def login = column[String]("login")

    def password = column[String]("password")

    def * = (id, login, password) <> ((User.apply _).tupled, User.unapply)

  }

  val users = TableQuery[Users]

  def create(id: Int, login: String, password: String): Future[User] = db.run {
    (users.map(c => (c.login, c.password))
      returning users.map(_.id)
      into { case ((`login`, `password`), `id`) => User(id, login, password) }
      ) += (login, password)
  }

  def insert(user: User): Future[Unit] = dbConfig.db.run(users += user).map { _ => () }

  def list(): Future[Seq[User]] = db.run {
    users.result
  }

  def delete(id: Int): Future[Unit] = db.run(users.filter(_.id === id).delete).map(_ => ())

  def findById(id: Int): Future[Option[User]] = db.run(users.filter(_.id === id).result.headOption)

  def update(id: Int, user: User): Future[Unit] = {
    val userUpdated: User = user.copy(id)
    db.run(users.filter(_.id === id).update(userUpdated)).map(_ => ())
  }

}
