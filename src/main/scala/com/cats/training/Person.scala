package com.cats.training

import cats.Functor
import com.cats.training.session2.Equal

object Person {

  implicit val personEqual = new Equal[Person] {
    override def equal(a: Person, b: Person): Boolean =
      a.name == b.name && a.email == b.email
  }


}

final case class Person(name: String, email: String) {
  object toXml
}
