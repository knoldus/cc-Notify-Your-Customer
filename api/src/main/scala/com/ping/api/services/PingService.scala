package com.ping.api.services

import com.ping.api.producer.PingProducer
import com.ping.json.JsonHelper
import com.ping.kafka.KafkaProducerApi
import com.ping.kafka.Topics._
import com.ping.models._

import scala.concurrent.Future


trait PingService extends JsonHelper {
  val pingProducer: KafkaProducerApi


  def processPing(ping: Ping): Future[PingResponse] = {
    ???
  }

  private def sendMail(mail: Option[PingEmail]) = {
    mail.map { mailMsg =>
      pingProducer.send(topicMail, write(mailMsg))
    }
  }

  private def sendSlackMessage(slack: Option[PingSlack]) = {
    slack.map { slackMsg =>
      pingProducer.send(topicSlack, write(slackMsg))
    }
  }

  private def sendPhoneMessage(message: Option[PingMessage]) = {
    message.map { msg =>
      pingProducer.send(topicMessage, write(msg))
    }
  }

}

object PingServiceImpl {
  def apply(pingProducerImpl: KafkaProducerApi) = new PingService {
    val pingProducer: KafkaProducerApi = pingProducerImpl
  }
}