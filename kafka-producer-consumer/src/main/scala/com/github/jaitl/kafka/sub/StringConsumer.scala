package com.github.jaitl.kafka.sub

import java.util.Properties

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer

import scala.collection.JavaConverters._


object StringConsumer {
  def main(args: Array[String]): Unit = {
    val props = new Properties()
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    props.put(ConsumerConfig.CLIENT_ID_CONFIG, "ScalaProducerExample")
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group")
    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true")
    props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000")


    val consumer = new KafkaConsumer[String, String](props)
    consumer.subscribe(Seq(StringProducer.topic).asJava)

    while(true) {
      val records = consumer.poll(1000)
      for (record <- records.asScala) {
        System.out.println("Received message: (" + record.key() + ", " + record.value() + ") at offset " + record.offset())
      }
    }

    consumer.close()
  }
}
