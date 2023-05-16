package com.cats.training.JSONExercise

import com.cats.training.Person

trait JSWriter[A] {
  def write(a: A): JsValue
}

object JSWriter {
  import com.cats.training.JSONExercise.JsValueInstances.{Anonymous, User}

  implicit object VisitorWriter extends JSWriter[Visitor] {
    def write(value: Visitor) = value match {
      case anon: Anonymous => JsUtil.toJson(anon)
      case user: User      => JsUtil.toJson(user)
    }
  }
}

object JsUtil {
  def toJson[A](a: A)(implicit writer: JSWriter[A]) = {
    writer write a
  }
}
