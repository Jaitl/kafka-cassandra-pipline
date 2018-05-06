package com.github.jaitl.kafka.sub

import java.util.Properties

import com.github.jaitl.kafka.model.ScalaPerson
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer

object ScalaPersonConsumer {

  import com.ovoenergy.kafka.serialization.circe._
  import io.circe.generic.auto._

  import scala.collection.JavaConverters._

  def main(args: Array[String]): Unit = {
    val props = new Properties()
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    props.put(ConsumerConfig.CLIENT_ID_CONFIG, "ScalaProducerExample")
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "test2-group")
    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true")
    props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000")

    val consumer = new KafkaConsumer(props, new StringDeserializer, circeJsonDeserializer[ScalaPerson])

    consumer.subscribe(Seq(ScalaPersonProducer.topic).asJava)

    while(true) {
      val records = consumer.poll(1000)
      for (record <- records.asScala) {
        System.out.println("Received message: (" + record.key() + ", " + record.value() + ") at offset " + record.offset())
      }
    }
  }
}
