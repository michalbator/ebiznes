package forms

import models.Book

object BookForm {
  import play.api.data.Forms._
  import play.api.data.Form

  case class Data(isbn: String, title: String, author: String)

  val bookForm = Form(
    mapping(
      "id" -> number,
      "isbn" -> nonEmptyText,
      "title" -> nonEmptyText,
      "author" -> number
    )(Book.apply)(Book.unapply)
  )
}