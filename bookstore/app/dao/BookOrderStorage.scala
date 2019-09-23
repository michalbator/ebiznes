package dao

import javax.inject.Inject
import models.BookOrder
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.JdbcProfile

import scala.concurrent.{ ExecutionContext, Future }

class BookOrderStorage @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {

  class BookOrders(tag: Tag) extends Table[BookOrder](tag, "bookOrders") {

    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def book = column[Int]("book")

    def idOrder = column[Int]("idOrder")

    def * = (id, book, idOrder) <> ((BookOrder.apply _).tupled, BookOrder.unapply)

  }

  val bookOrders = TableQuery[BookOrders]

  //  def create(id: Int, book: Int, idOrder: Int): Future[BookOrder] = db.run {
  //    (bookOrders.map(c => (c.book, c.idOrder))
  //      returning bookOrders.map(_.id)
  //      into { case ((`book`, `idOrder`), `id`) => BookOrder(id, book, idOrder) }
  //    ) += (book, idOrder)
  //  }

  def insert(bookOrder: BookOrder): Future[Unit] = db.run(bookOrders += bookOrder).map { _ => () }

  def list(): Future[Seq[BookOrder]] = db.run {
    bookOrders.result
  }

  def delete(id: Int): Future[Unit] = db.run(bookOrders.filter(_.id === id).delete).map(_ => ())

  def findById(id: Int): Future[Option[BookOrder]] = db.run(bookOrders.filter(_.id === id).result.headOption)

  def update(id: Int, bookOrder: BookOrder): Future[Unit] = {
    val bookOrderUpdated: BookOrder = bookOrder.copy(id)
    db.run(bookOrders.filter(_.id === id).update(bookOrderUpdated)).map(_ => ())
  }
}
