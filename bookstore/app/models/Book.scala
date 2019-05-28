package models

import forms.BookForm
import play.api.libs.json.Json

case class Book(id: Int, isbn: String, title: String, author: Int)

object Book {
  implicit val bookFormat = Json.format[Book]
}