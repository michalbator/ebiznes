package forms

import models.Order

object OrderForm {
  import play.api.data.Form
  import play.api.data.Forms._

  case class Data(user: Int, status: String)

  val formOrder = Form(
    mapping(
      "id" -> number,
      "user" -> number,
      "status" -> nonEmptyText
    )(Order.apply)(Order.unapply)
  )
}