package controllers

import dao._
import javax.inject.Inject
import models._
import play.api.mvc._
import forms.BookForm.bookForm
import forms.UserForm.formUser
import forms.CountryForm.countryForm
import forms.AuthorForm.authorForm
import forms.OrderForm.formOrder
import play.api.libs.functional.syntax._

import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.functional.syntax._
import play.api.libs.json.Json

class AdminController @Inject()(cc: ControllerComponents, authors: AuthorStorage, countries: CountryStorage, books: BookStorage, orders: OrderStorage, users: UserStorage) extends AbstractController(cc) {

  def getAllProducts() = Action.async { implicit request =>
    implicit val fooWrites = Json.writes[Book]
    books.list().map({ book =>
      Ok(Json.toJson(book))
    })
  }

  def addProduct = Action { implicit request =>
    val book: Book = bookForm.bindFromRequest().get
    books.insert(book)
    implicit val bookWrites = Json.writes[Book]
    Ok(Json.toJson(book))
  }

  def editProduct(id: Integer) = Action.async { implicit request =>
    val book: Book = bookForm.bindFromRequest().get
    books.update(book.id, book)
    implicit val bookWrites = Json.writes[Book]
    books.findById(id).map({ book =>
      Ok(Json.toJson(book))
    })
  }

  def deleteProduct(id: Integer) = Action {
    books.delete(id)
    Ok(Json.toJson("ok"))
  }

  def getAllOrders = Action {
    implicit val orderWrites = Json.writes[Order]
    orders.list().map({ order =>
      Ok(Json.toJson(order))
    })
    Ok(views.html.index("Controller OK"))
  }

  def editOrder(id: Integer) = Action.async { implicit request =>
    val order: Order = formOrder.bindFromRequest().get
    orders.update(order.id, order)
    implicit val ordersWrites = Json.writes[Order]
    orders.findById(id).map({ order =>
      Ok(Json.toJson(order))
    })
  }

  def showOrder(id: Integer) = Action.async { implicit request =>
    implicit val ordersWrites = Json.writes[Order]
    orders.findById(id).map({ order =>
      Ok(Json.toJson(order))
    })
  }

  def editUser(id: Integer) = Action.async { implicit request =>
    val user: User = formUser.bindFromRequest().get
    users.update(user.id, user)
    implicit val userWrites = Json.writes[User]
    users.findById(id).map({ user =>
      Ok(Json.toJson(user))
    })
  }

  def addUser() = Action { implicit request =>
    val user: User = formUser.bindFromRequest().get
    users.insert(user)
    implicit val userWrites = Json.writes[User]
    Ok(Json.toJson(user))
  }

  def getAllUsers() = Action.async { implicit request =>
    implicit val userWrites = Json.writes[User]
    users.list().map({ user =>
      Ok(Json.toJson(user))
    })
  }

  def deleteUser(id: Integer) = Action {
    users.delete(id)
    Ok(Json.toJson("ok"))
  }

  def addCountry = Action { implicit request =>
    val country: Country = countryForm.bindFromRequest().get
    countries.insert(country)
    implicit val countryWrites = Json.writes[Country]
    Ok(Json.toJson(country))
  }

  def getAllCountries() = Action.async { implicit request =>
    implicit val countryWrites = Json.writes[Country]
    countries.list().map({ country =>
      Ok(Json.toJson(country))
    })
  }

  def getAuthor(id: Integer) = Action.async {
    implicit val authorWrites = Json.writes[Author]
    authors.findById(id).map({ author =>
      Ok(Json.toJson(author))
    })
  }

  def getAllAuthors() = Action.async { implicit request =>
    implicit val authorWrites = Json.writes[Author]
    authors.list().map({ author =>
      Ok(Json.toJson(author))
    })
  }

  def deleteAuthor(id: Integer) = Action {
    authors.delete(id)
    Ok(Json.toJson("ok"))
  }

  def addAuthor() = Action { implicit request =>
    val author: Author = authorForm.bindFromRequest().get
    authors.insert(author)
    implicit val authorWrites = Json.writes[Author]
    Ok(Json.toJson(author))
  }
}
