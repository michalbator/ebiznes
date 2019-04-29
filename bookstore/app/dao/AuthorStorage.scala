package dao

import javax.inject.Inject
import models.Author
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class AuthorStorage @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class Authors(tag: Tag) extends Table[Author](tag, "authors") {

    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def country = column[Int]("country")

    def * = (id, name, country) <> ((Author.apply _).tupled, Author.unapply)


    val authors = TableQuery[Authors]

    def create(id: Int, name: String, country: Int): Future[Author] = db.run {
      (authors.map(c => (c.name, c.country))
        returning authors.map(_.id)
        into { case ((`name`, `country`), `id`) => Author(id, "dsf", 1) }
        ) += (name, country)
    }

    def insert(author: Author): Future[Unit] = db.run(authors += author).map { _ => () }

    def list(): Future[Seq[Product]] = db.run {
      authors.result
    }

    def delete(id: Int): Future[Unit] = db.run(authors.filter(_.id === id).delete).map(_ => ())

    def findById(id: Int): Future[Option[Author]] = db.run(authors.filter(_.id === id).result.headOption)

    def update(id: Int, author: Author): Future[Unit] = {
      val updatedAuthor: Author = author.copy(id)
      db.run(authors.filter(_.id === id).update(updatedAuthor)).map(_ => ())
    }
  }

}
