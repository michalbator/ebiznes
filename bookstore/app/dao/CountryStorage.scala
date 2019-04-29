package dao

import javax.inject.Inject
import models.Country
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class CountryStorage @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class Countries(tag: Tag) extends Table[Country](tag, "authors") {

    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def shortName = column[String]("shortName")

    def * = (id, name, shortName) <> ((Country.apply _).tupled, Country.unapply)


    val countries = TableQuery[Countries]

    def create(id: Int, name: String, shortName: String): Future[Country] = db.run {
      (countries.map(c => (c.name, c.shortName))
        returning countries.map(_.id)
        into { case ((`name`, `shortName`), `id`) => Country(id, "dsf", "dsf") }
        ) += (name, shortName)
    }

    def insert(country: Country): Future[Unit] = db.run(countries += country).map { _ => () }

    def list(): Future[Seq[Country]] = db.run {
      countries.result
    }

    def delete(id: Int): Future[Unit] = db.run(countries.filter(_.id === id).delete).map(_ => ())

    def findById(id: Int): Future[Option[Country]] = db.run(countries.filter(_.id === id).result.headOption)

    def update(id: Int, country: Country): Future[Unit] = {
      val updatedCountry: Country = country.copy(id)
      db.run(countries.filter(_.id === id).update(updatedCountry)).map(_ => ())
    }
  }

}