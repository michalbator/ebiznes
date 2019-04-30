package models

import play.api.libs.json.Json

case class BookOrder(id: Int, book: Int, idOrder: Int)

object BookOrder {
  implicit val bookOrderFormat = Json.format[BookOrder]
}