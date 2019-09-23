package controllers

import dao._
import javax.inject.Inject
import models._
import play.api.mvc._
import forms.BookForm.bookForm
import forms.CountryForm.countryForm
import forms.AuthorForm.authorForm
import forms.OrderForm.formOrder
import models.services.UserService
import play.api.libs.functional.syntax._
import scala.concurrent.duration._

import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.functional.syntax._
import play.api.libs.json.Json

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class AdminController @Inject() (cc: ControllerComponents, authors: AuthorStorage, countries: CountryStorage, books: BookStorage, orders: OrderStorage, users: UserService) extends AbstractController(cc) {

  def getAllProducts() = Action.async { implicit request =>
    implicit val fooWrites = Json.writes[Book]
    books.list().map({ book =>
      Ok(Json.toJson(book))
    })
  }

  def addProduct = Action { implicit request =>
    val book: Book = bookForm.bindFromRequest().get
    Await.result(books.insert(book), 20.seconds)
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

  def getAllOrders = Action.async { implicit reqest =>
    implicit val orderWrites = Json.writes[Order]
    orders.list().map({ order =>
      Ok(Json.toJson(order))
    })
  }

  def getUserOrders(user: String) = Action.async { implicit reqest =>
    implicit val orderWrites = Json.writes[Order]
    orders.findByUser(user).map({ order =>
      Ok(Json.toJson(order))
    })
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

  def addOrder() = Action { implicit request =>
    val order: Order = formOrder.bindFromRequest().get
    orders.insert(order)
    implicit val OrderWrites = Json.writes[Order]
    Ok(Json.toJson(order))
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
