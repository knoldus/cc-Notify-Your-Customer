package com.ping.http

import com.ping.json.JsonHelper
import org.json4s.JValue

/**
  * Created by girish on 30/6/17.
  */
case class PingHttpResponseData(
                             code: Int,
                             data: Option[JValue] = None,
                             message: Option[String] = None
                           )

object PingHttpResponse extends JsonHelper {

  def OK(data: AnyRef): String = write(PingHttpResponseData(code = 200, data = Some(parse(write(data)))))

  def ERROR(msg: String): String = write(PingHttpResponseData(code = 200, message = Some(msg)))

}
