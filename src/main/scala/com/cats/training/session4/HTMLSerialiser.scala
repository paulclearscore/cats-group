package com.cats.training.session4

import com.cats.training.{Order, Person}
import com.cats.training.session2.CreditCard

// type class
trait HTMLSerialiser[A] {
  def toHtml(a: A): String
}

object HTMLSerialiser {
  def apply[A](implicit instance: HTMLSerialiser[A]): HTMLSerialiser[A] =
    instance
}

object HTMLer {
  def apply[A](a: A)(implicit instance: HTMLSerialiser[A]): String =
    instance.toHtml(a)
}

object Enrichment {
  implicit class PersonEnrichment(person: Person) {

    def printHTML = {
      println(s"""
         |<Person>
         |  <Name>${person.name}</Name>
         |  <Email>${person.email}</Email>
         |</Person>
         |""".stripMargin)
    }

    def toHTML(implicit instance: HTMLSerialiser[Person]): String =
      instance.toHtml(person)
  }

}

object Instances {
  // type class instance for Person
  implicit val personSerialiser: HTMLSerialiser[Person] =
    new HTMLSerialiser[Person] {
      override def toHtml(person: Person): String =
        s"""
         |<div>
         |  <h1>${person.name}</h1>
         |  <email>${person.email}</email>
         |</div>
         |""".stripMargin
    }

  // type class instance for Card
  implicit val cardSerialiser: HTMLSerialiser[CreditCard] =
    new HTMLSerialiser[CreditCard] {
      override def toHtml(card: CreditCard): String =
        s"""
            |<h1>${card.balance}</h1>
            |""".stripMargin
    }

  implicit val orderSerialiser: HTMLSerialiser[Order] =
    new HTMLSerialiser[Order] {
      override def toHtml(order: Order): String =
        s"""
           |<h1>${order.units}</h1>
           |<h2>${order.unitPrice}</h2>
           |""".stripMargin
    }

  implicit val orderSerialiser2: HTMLSerialiser[Order] =
    new HTMLSerialiser[Order] {
      override def toHtml(order: Order): String =
        s"""
           |<h1>**********************/h1>

           |""".stripMargin
    }

}
