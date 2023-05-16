package com.cats.training.session7
import cats.Show
import cats.implicits._

import java.util.Date // for Show
//import cats.instances.string._ // for Show
import cats.implicits._

object CatShow extends App {
//  implicit val dateInstance: Show[Date] = new Show[Date] {
//    override def show(t: Date): String = s"${t.getTime}ms since the epoch."
//  }

  // Cats gives us a helper method for instantiating type class objects; v is equivalent to ^
  implicit val dateShow: Show[Date] =
    Show.show(date => s"${date.getTime}ms since the epoch.")

  implicit val garfShow: Show[Cat] =
    Show.show(cat => s"${cat.name} is a ${cat.age} year-old ${cat.color} cat. Fluffy!!!")

  val showInt = Show.apply[Int]

  val garfield = Cat("Garfield", 44, "ginger and black")

  println(garfield.show)

  val randomDate = new Date()

  println(randomDate.show)

  val showDate = Show.apply[Date]

}
