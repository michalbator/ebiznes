package controllers

import dao.BookStorage
import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}
import services.Counter
import models.Book

//noinspection TypeAnnotation
@Singleton
class UserController @Inject()(cc: ControllerComponents,
                               counter: Counter, books : BookStorage) extends AbstractController(cc) {

  def loginForm = Action {
    val book = Book(1, "kk", "d", 2)
    books.insert(book)
    Ok(views.html.index("index"))
  }

  def loginAction = Action {
    Ok(views.html.index("index"))
  }

  def logoutAction = Action {
    Ok(views.html.index("index"))
  }
}