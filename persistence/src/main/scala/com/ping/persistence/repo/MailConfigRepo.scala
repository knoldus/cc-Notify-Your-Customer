package com.ping.persistence.repo

import com.ping.models.RDMailConfig
import com.ping.persistence.mapping.MailConfigMapping
import com.ping.persistence.provider.{DBProvider, PostgresDBProvider}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


trait MailConfigRepo extends MailConfigMapping {
  this: DBProvider =>

  import driver.api._

  def insert(mailConfig: RDMailConfig): Future[RDMailConfig] = withTransaction {
    (mailConfigInfoAutoInc += mailConfig) map { incId =>
      mailConfig.copy(id = incId)
    }
  }

  def get(id: Long): Future[Option[RDMailConfig]] = withTransaction {
    mailConfigInfo.filter(_.id === id).result.headOption
  }

  def getByClientId(clientId: Long): Future[Option[RDMailConfig]] = withTransaction {
    mailConfigInfo.filter(_.clientId === clientId).result.headOption
  }

  def update(rdMailConfig: RDMailConfig): Future[Int] = withTransaction {
    mailConfigInfo.filter(_.id === rdMailConfig.id).update(rdMailConfig)
  }

  def delete(clientId: Long): Future[Int] = withTransaction {
    mailConfigInfo.filter(_.clientId === clientId).delete
  }

}

object MailConfigRepo extends MailConfigRepo with PostgresDBProvider
