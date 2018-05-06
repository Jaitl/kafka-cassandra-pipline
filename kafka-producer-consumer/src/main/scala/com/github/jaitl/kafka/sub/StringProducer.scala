package com.github.jaitl.kafka.sub

import java.util.Properties

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord

/**
  * create topic:
  * docker-compose exec kafka bash
  * /opt/kafka/bin/kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic string-data
  *
  * topic list:
  * /opt/kafka/bin/kafka-topics.sh --list --zookeeper zookeeper:2181
  */
object StringProducer {
  val topic = "string-data"

  def main(args: Array[String]): Unit = {
    val props = new Properties()
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    props.put(ProducerConfig.CLIENT_ID_CONFIG, "ScalaProducerExample")
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer[String, String](props)

    val count = 100

    for (i <- 0 to count) {
      val key = s"$i key"
      val value = s"$i value"
      val data = new ProducerRecord[String, String](topic, key, value)

      producer.send(data)
    }

    producer.close()
  }
}
