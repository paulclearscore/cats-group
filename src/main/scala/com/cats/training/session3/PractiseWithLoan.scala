package com.cats.training.session3
import com.cats.training.Loan
//import com.cats.training.session3.JsonOps.IWriteJson
import com.cats.training.session3.implClasses.JsonOps

import scala.util.Random

object PractiseWithLoan extends App {

  def convert(value: String)(implicit writer: JsonWriter[String]): Json = {
    writer.write(value)
  }
  println("Session 4")
  println(convert("hello"))
  println(JsonWriter("Hello"))
  println(JsonWriter(Loan(1000, 36)))
  println(JsonWriter[Loan].write(Loan(2, 3)))

//  println(IWriteJson(Loan(2000, 12)))

  private val paulsHeftyLoan = Loan(3272197, 12)

  paulsHeftyLoan.toJson

  JsonWriter[Int](101)
  JsonWriter(10100)
  JsonWriter[Int].write(55)

  JsonWriter[Int].write(1000)
  101.toJson
  (-100).toJson
  Random.nextInt().toJson
  println("Test".toJson)

  def pageTemplate[A: JsonWriter](body: A): String = {
    val renderedBody = body.toJson
    val writeExtract = implicitly[JsonWriter[A]]
//    writeExtract.write()
    s"I am json $renderedBody"
  }

  println(pageTemplate(paulsHeftyLoan))

}

sealed trait Json
final case class JsObject(get: Map[String, Json]) extends Json
final case class JsString(get: String) extends Json
final case class JsNumber(get: Double) extends Json
case object JsNull extends Json
