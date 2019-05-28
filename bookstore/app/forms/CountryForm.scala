package forms

import models.Country

object CountryForm {

  import play.api.data.Form
  import play.api.data.Forms._

  case class Data(name: String, shortName: String)

  val countryForm = Form(
    mapping(
      "id" -> number,
      "name" -> nonEmptyText,
      "shortName" -> nonEmptyText
    )(Country.apply)(Country.unapply)
  )
}