package com.cats.training.session4

import cats.data.Writer
import cats.implicits.catsSyntaxOptionId
import com.cats.training.{Order, Person}
import com.cats.training.session2.CreditCard
import com.cats.training.session4.Enrichment.PersonEnrichment
//import com.cats.training.session4.Instances.orderSerialiser

object Session4 extends App {

// type class HTMLSerialiser interfaces - methods

  def personHTMLify(
      person: Person
  )(implicit writer: HTMLSerialiser[Person]): String = {
    writer.toHtml(person)
  }

  // or a generic method

  def htmlify[A](data: A)(implicit writer: HTMLSerialiser[A]): String = {
    writer.toHtml(data)
  }

  def htmlifyWithContext[A : HTMLSerialiser](data: A): String = {
    implicitly[HTMLSerialiser[A]].toHtml(data)
  }

  def orderHTMLify(
      order: Order
                  )(implicit writer: HTMLSerialiser[Order]): String = {
    writer.toHtml((order))
  }




  val person1 = Person("Sally", "email@s.com")
  val credCard1 = CreditCard(3000)
  import Instances.personSerialiser
  import Instances.cardSerialiser

  println(personHTMLify(person1))
  println(htmlify(person1))

  HTMLSerialiser[Person].toHtml(person1)
  HTMLer(person1)
  println(htmlify[CreditCard](credCard1))


  // using with Enrichment
  person1.printHTML
  person1.toHTML

  val mosOrder = Order(23, 3000)

  import Instances.orderSerialiser2
  println(orderHTMLify(mosOrder))
  Some(person1)
  person1.some

}
