package com.ping.persistence.provider

import slick.dbio
import slick.dbio.Effect.Transactional
import slick.jdbc.JdbcProfile

import scala.concurrent.Future

trait DBProvider {

  val driver: JdbcProfile

  import driver.api._

  val db: Database

  def withTransaction[R](transactionFunc:  => DBIOAction[R, _ <: dbio.NoStream, _ <: Effect with Transactional]): Future[R] = {
    val query = transactionFunc
    db.run(query.transactionally)
  }

}
