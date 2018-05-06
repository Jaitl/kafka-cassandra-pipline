package com.github.jaitl.kafka.model

case class FullPersonInfo(age: Int, jobs: Seq[String])

case class Person(ID: String, firstName: String, fullInfo: FullPersonInfo)
