package com.cats.training.JSONExercise

sealed trait JsValue {
  def stringify: String = this match {
    case JsString(value) => "\"" + value.replaceAll("\\|\"", "\\\\$1") + "\""
    case JsObject(values) =>
      values
        .map { case (name, value) => "\"" + name + "\":" + value.stringify }
        .mkString("{", ",", "}")
  }
}
final case class JsObject(values: Map[String, JsValue]) extends JsValue
final case class JsString(value: String) extends JsValue
