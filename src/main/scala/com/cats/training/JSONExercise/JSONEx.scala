package com.cats.training.JSONExercise

import java.util.Date

object JSONEx extends App {
  println("JSON")

  println(JsString("Testing").stringify)

  val obj = JsObject(
    Map(
      "item1" -> JsString("dog"),
      "item2" -> JsString("badger"),
      "item3" -> JsString("Star Destroyer")
    )
  )
  println(obj.stringify)
  val visitors: Seq[Visitor] =
    Seq(Anonymous("001", new Date), User("003 ", "dave@xample.com", new Date))
  println(visitors(1))


  visitors.foreach(vis => println(JsUtil.toJson(vis).stringify))

}
