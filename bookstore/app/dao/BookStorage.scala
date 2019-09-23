package dao

import javax.inject.Inject
import models.{ Book, Country }
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.JdbcProfile

import scala.concurrent.{ ExecutionContext, Future }

class BookStorage @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {

  class Books(tag: Tag) extends Table[Book](tag, "books") {

    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def isbn = column[String]("isbn", O.Unique)

    def title = column[String]("title")

    def author = column[Int]("author")

    def * = (id, isbn, title, author) <> ((Book.apply _).tupled, Book.unapply)

  }

  val books = TableQuery[Books]

  //  def create(id: Int, isbn: String, title: String, author: Int): Future[Book] = db.run {
  //    (books.map(c => (c.isbn, c.title, c.author))
  //      returning books.map(_.id)
  //      into { case ((`isbn`, `title`, `author`), `id`) => Book(id, "dsf", "dsf", 1) }
  //    ) += (isbn, title, author)
  //  }

  def insert(book: Book): Future[String] = db.run(books += book).map(res => "Added")

  def list(): Future[Seq[Book]] = db.run {
    books.result
  }

  def delete(id: Int): Future[Unit] = db.run(books.filter(_.id === id).delete).map(_ => ())

  def findById(id: Int): Future[Option[Book]] = db.run(books.filter(_.id === id).result.headOption)

  def update(id: Int, book: Book): Future[Unit] = {
    val updatedBook: Book = book.copy(id)
    db.run(books.filter(_.id === id).update(updatedBook)).map(_ => ())
  }
}