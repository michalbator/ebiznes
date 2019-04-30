package controllers

import dao.{BookStorage, OrderStorage, UserStorage}
import javax.inject.Inject
import models.{Book, Order, User}
import play.api.mvc
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class AdminController @Inject()(cc: ControllerComponents, books: BookStorage, orders: OrderStorage, users: UserStorage) extends AbstractController(cc) {


  def getAllProducts = Action {
    books.list()
    Ok(views.html.index("Controller OK"))
  }

  def addProductForm() = Action {
    Ok(views.html.index("Controller OK"))
  }

  def editProductForm() = Action {
    Ok(views.html.index("Controller OK"))
  }

  def addProduct() = Action {
    val book = Book(1, "kk", "d", 2)
    val result = books.insert(book)
    Await.result(result, Duration.Inf)
    Ok(views.html.index("Controller OK"))
  }

  def editProduct(id: Integer) = Action {
    val book = Book(1, "df", "d", 5)
    books.update(id, book)
    Ok(views.html.index("Controller OK"))
  }

  def deleteProduct(id: Integer) = Action {
    Ok(views.html.index("Controller OK"))
  }

  def getAllOrders = Action {
    orders.list()
    Ok(views.html.index("Controller OK"))
  }

  def editOrder(id: Integer) = Action {
    val order = Order(1, 2, "d")
    val result = orders.insert(order)
    Ok(views.html.index("Controller OK"))
  }

  def showOrder(id: Integer) = Action {
    orders.findById(id)
    Ok(views.html.index("Controller OK"))
  }

  def getAllUser = Action {
    users.list()
    Ok(views.html.index("Controller OK"))
  }

  def editUserForm() = Action {
    Ok(views.html.index("Controller OK"))
  }

  def addUserForm() = Action {
    Ok(views.html.index("Controller OK"))
  }

  def editUser(id: Integer) = Action {
    users.findById(id)
    Ok(views.html.index("Controller OK"))
  }

  def addUser(id: Integer) = Action {
    val user = User(1, "fd", "df")
    users.insert(user)
    Ok(views.html.index("Controller OK"))
  }

  def deleteUser(id: Integer) = mvc.Action {
    Ok(views.html.index("Controller OK"))
  }
}
