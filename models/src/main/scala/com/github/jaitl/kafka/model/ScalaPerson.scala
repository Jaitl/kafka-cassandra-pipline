package com.github.jaitl.kafka.model

case class ScalaFullPersonInfo(age: Int, jobs: Seq[String])

case class ScalaPerson(ID: String, firstName: String, fullInfo: ScalaFullPersonInfo)
