package controllers

import dao._
import javax.inject.Inject
import play.api.mvc._
import play.api.libs.json.Json

//noinspection TypeAnnotation
class UserController @Inject() (
  cc: ControllerComponents,
  books: BookStorage, orders: OrderStorage) extends AbstractController(cc) {

  def loginForm = Action {
    Ok(Json.toJson("User actions not yet implemented"))
  }

  def loginAction = Action {
    Ok(Json.toJson("User actions not yet implemented"))
  }

  def logoutAction = Action {
    Ok(Json.toJson("User actions not yet implemented"))
  }

  def registerForm() = Action {
    Ok(Json.toJson("User actions not yet implemented"))
  }

  def registerAction() = Action {
    Ok(Json.toJson("User actions not yet implemented"))
  }

  def getAllBooks = Action {
    Ok(Json.toJson("User actions not yet implemented"))
  }

  def getBook(id: Integer) = Action {
    Ok(Json.toJson("User actions not yet implemented"))
  }

  def buyBook(id: Integer) = Action {
    Ok(Json.toJson("User actions not yet implemented"))
  }

  def cancelOrder() = Action {
    Ok(Json.toJson("User actions not yet implemented"))
  }

  def getAllOrders = Action {
    Ok(Json.toJson("User actions not yet implemented"))
  }

  def getOrder(id: Integer) = Action {
    Ok(Json.toJson("User actions not yet implemented"))
  }

  def addOrder() = Action { implicit request =>
    Ok(Json.toJson("User actions not yet implemented"))
  }
}