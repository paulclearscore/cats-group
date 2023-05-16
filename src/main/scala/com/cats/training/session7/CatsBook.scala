package com.cats.training.session7

import java.util.Date

object CatsBook extends App {

  println("Cats Book")

  val snuggles = Cat("Snuggles", 5, "ginger");

  {

    import PrintableSyntax._
    import PrintableInstances._

    Printable.print(snuggles)
    snuggles.print
  }

  {
    import PrintableSyntax._
    import PrintableInstances._

    val someDate: Date = new Date()

    println(someDate.format)
  }
}
