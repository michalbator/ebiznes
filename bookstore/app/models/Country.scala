package models

import play.api.libs.json.Json

case class Country(id: Int, name: String, shortName: String)

object Country {
  implicit val countryFormat = Json.format[Country]
}
