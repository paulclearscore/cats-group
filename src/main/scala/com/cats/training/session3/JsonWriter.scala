package com.cats.training.session3

import com.cats.training.Loan

trait JsonWriter[A] {
  def write(value: A): Json
}

//object JsonOps {
//
//  object IWriteJson {
//    def apply[A](a: A)(implicit writer: JsonWriter[A]): Json = {
//      writer.write(a)
//    }
//
//  }
//
//}

object JsonWriter {

  def apply[A](value: A)(implicit writer: JsonWriter[A]): Json = {
    writer.write(value)
  }

  def apply[A]()(implicit writer: JsonWriter[A]): JsonWriter[A] = {
    writer
  }

  implicit val stringWriter = new JsonWriter[String] {
    override def write(value: String): Json = JsString(value)
  }

  implicit val loanWriter = new JsonWriter[Loan] {
    override def write(value: Loan): Json = JsObject(
      Map("amount" -> JsNumber(value.amount), "term" -> JsNumber(value.term))
    )
  }

  implicit val intWriter = new JsonWriter[Int] {
    override def write(value: Int): Json = JsNumber(value)
  }
}
