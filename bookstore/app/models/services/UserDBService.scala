package models.services

import com.google.inject.Inject
import models.{ UserDB, UsersDB }

import scala.concurrent.{ ExecutionContext, Future }

class UserDBService @Inject() (usersDB: UsersDB)(implicit executionContext: ExecutionContext) {
  def listAll: Future[Seq[UserDB]] = {
    usersDB.listAll
  }
  def add(userDB: UserDB): Future[String] = {
    usersDB.add(userDB)
  }
  def exist(provider: String, userID: String): Future[Option[UserDB]] =
    {
      usersDB.exist(provider, userID).flatMap(x => {
        x match {
          case Some(i) => {
            Future.successful(Option(i))
          }
          case None => {
            Future.successful(None)
          }
        }
      })
    }
  def get(id: Int): Future[Option[UserDB]] = {
    usersDB.get(id)
  }
}

