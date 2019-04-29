package models

import play.api.libs.json.Json

case class Author(id: Int, name: String, country: Int)

object Author {
  implicit val authorFormat = Json.format[Author]
}