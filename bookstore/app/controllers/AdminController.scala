package controllers

import javax.inject.Inject
import play.api.mvc
import play.api.mvc.{AbstractController, ControllerComponents}

class AdminController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {


  def getAllProducts = Action {
    Ok(views.html.index("Controller OK"))
  }

  def addProductForm() = Action {
    Ok(views.html.index("Controller OK"))
  }

  def editProductForm() = Action {
    Ok(views.html.index("Controller OK"))
  }

  def addProduct() = Action {
    Ok(views.html.index("Controller OK"))
  }

  def editProduct(id: Integer) = Action {
    Ok(views.html.index("Controller OK"))
  }

  def deleteProduct(id: Integer) = Action {
    Ok(views.html.index("Controller OK"))
  }

  def getAllOrders = Action {
    Ok(views.html.index("Controller OK"))
  }

  def editOrder(id: Integer) = Action {
    Ok(views.html.index("Controller OK"))
  }

  def showOrder(id: Integer) = Action {
    Ok(views.html.index("Controller OK"))
  }

  def getAllUser = Action {
    Ok(views.html.index("Controller OK"))
  }

  def editUserForm() = Action {
    Ok(views.html.index("Controller OK"))
  }

  def addUserForm() = Action {
    Ok(views.html.index("Controller OK"))
  }

  def editUser(id: Integer) = Action {
    Ok(views.html.index("Controller OK"))
  }

  def addUser(id: Integer) = Action {
    Ok(views.html.index("Controller OK"))
  }

  def deleteUser(id: Integer) = mvc.Action {
    Ok(views.html.index("Controller OK"))
  }
}
