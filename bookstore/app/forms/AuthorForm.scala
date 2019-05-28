package forms

import models.Author

object AuthorForm {
  import play.api.data.Forms._
  import play.api.data.Form

  case class Data(name: String, country: Int)

  val authorForm = Form(
    mapping(
      "id" -> number,
      "name" -> nonEmptyText,
      "country" -> number
    )(Author.apply)(Author.unapply)
  )
}