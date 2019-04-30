package controllers


import dao.BookStorage
import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}
import services.Counter
import models.Book

import scala.concurrent.Await
import scala.concurrent.duration.Duration

//noinspection TypeAnnotation
@Singleton
class UserController @Inject()(cc: ControllerComponents,
                               counter: Counter, books: BookStorage) extends AbstractController(cc) {

  def loginForm = Action {
    Ok(views.html.index("index"))
  }

  def loginAction = Action {
    Ok(views.html.index("Controller OK"))
  }

  def logoutAction = Action {
    Ok(views.html.index("Controller OK"))
  }

  def registerForm() = Action {
    Ok(views.html.index("Controller OK"))
  }

  def registerAction() = Action {
    Ok(views.html.index("Controller OK"))
  }

  def getAllBooks = Action {

    Ok(views.html.index("Controller OK"))
  }

  def getBook(id: Integer) = Action {
    Ok(views.html.index("Controller OK"))
  }

  def buyBook(id: Integer) = Action {
    Ok(views.html.index("Controller OK"))
  }

  def cancelOrder() = Action {
    Ok(views.html.index("Controller OK"))
  }

  def getAllOrders = Action {
    Ok(views.html.index("Controller OK"))
  }

  def getOrder(id: Integer) = Action {
    Ok(views.html.index("Controller OK"))
  }
}