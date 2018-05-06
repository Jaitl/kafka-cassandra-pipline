package com.github.jaitl.kafka.sub

import java.util.Properties

import com.github.jaitl.kafka.model.ScalaFullPersonInfo
import com.github.jaitl.kafka.model.ScalaPerson
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer

object ScalaPersonProducer {
  val topic = "scala-person"

  import com.ovoenergy.kafka.serialization.circe._
  import io.circe.generic.auto._

  def main(args: Array[String]): Unit = {
    val props = new Properties()
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    props.put(ProducerConfig.CLIENT_ID_CONFIG, "ScalaProducerExample")

    val producer = new KafkaProducer(props, new StringSerializer, circeJsonSerializer[ScalaPerson])

    val count = 100

    for (i <- 0 to count) {
      val id = s"$i"
      val value = ScalaPerson(id, "test", ScalaFullPersonInfo(i, Seq("1", "2")))

      val data = new ProducerRecord[String, ScalaPerson](topic, id, value)

      producer.send(data)
    }

    producer.close()
  }
}
