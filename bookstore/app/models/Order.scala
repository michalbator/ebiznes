package models

import play.api.libs.json.Json

case class Order(id: Int, user: String, status: String, book: Int)

object Order {
  implicit val orderFormat = Json.format[Order]
}