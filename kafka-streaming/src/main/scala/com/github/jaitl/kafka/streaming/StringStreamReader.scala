package com.github.jaitl.kafka.streaming

import java.util.Properties

import org.apache.kafka.streams.kstream.KStream

object StringStreamReader {
  import org.apache.kafka.common.serialization._
  import org.apache.kafka.streams._

  def main(args: Array[String]): Unit = {
    val settings = new Properties()
    settings.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    settings.put(StreamsConfig.APPLICATION_ID_CONFIG, "ScalaProducerExample")

    // Specify default (de)serializers for record keys and for record values.
    settings.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String.getClass.getName)
    settings.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String.getClass.getName)


    val builder = new StreamsBuilder()
    val sourceStream: KStream[String, String] = builder.stream(StringProducer.topic).mapValues {
      value: String => value + " mapped"
    }

    sourceStream.foreach({
      case (key, value) => println(s"key: $key, value: $value")
    })


    val streams = new KafkaStreams(builder.build(), settings)
    streams.start()
  }
}
