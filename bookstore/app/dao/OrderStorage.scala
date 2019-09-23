package dao

import javax.inject.Inject
import models.Order
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.JdbcProfile

import scala.concurrent.{ ExecutionContext, Future }

class OrderStorage @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {

  class Orders(tag: Tag) extends Table[Order](tag, "orders") {

    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def user = column[String]("user")

    def status = column[String]("status")

    def book = column[Int]("book")

    def * = (id, user, status, book) <> ((Order.apply _).tupled, Order.unapply)

  }

  val orders = TableQuery[Orders]

  //  def create(id: Int, user: Int, status: String): Future[Order] = db.run {
  //    (orders.map(c => (c.user, c.status))
  //      returning orders.map(_.id)
  //      into { case ((`user`, `status`), `id`) => Order(id, user, status) }
  //    ) += (user, status)
  //  }

  def insert(order: Order): Future[Unit] = db.run(orders += order).map { _ => () }

  def list(): Future[Seq[Order]] = db.run {
    orders.result
  }

  def delete(id: Int): Future[Unit] = db.run(orders.filter(_.id === id).delete).map(_ => ())

  def findById(id: Int): Future[Option[Order]] = db.run(orders.filter(_.id === id).result.headOption)

  def findByUser(user: String): Future[Seq[Order]] = db.run(orders.filter(_.user === user).result)

  def update(id: Int, order: Order): Future[Unit] = {
    val orderUpdated: Order = order.copy(id)
    db.run(orders.filter(_.id === id).update(orderUpdated)).map(_ => ())
  }
}
