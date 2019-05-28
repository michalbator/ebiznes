package forms

import models.User

object UserForm {
  import play.api.data.Form
  import play.api.data.Forms._

  case class Data(login: String, password: String)

  val formUser = Form(
    mapping(
      "id" -> number,
      "login" -> nonEmptyText,
      "password" -> nonEmptyText
    )(User.apply)(User.unapply)
  )
}