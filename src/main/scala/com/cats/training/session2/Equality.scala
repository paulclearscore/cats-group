package com.cats.training.session2

import com.cats.training.Person
import com.cats.training.session2.Equality.Animal

trait Equal[A] {
  def equal(a: A, b: A): Boolean
}

object Eq {
  def apply[A](v1: A, v2: A)(implicit equal: Equal[A]): Boolean =
    equal.equal(v1, v2)
}

object Equal {

  def apply[A]()(implicit instance: Equal[A]): Equal[A] =
    instance

  implicit val creditCardEqual = new Equal[CreditCard] {
    override def equal(a: CreditCard, b: CreditCard): Boolean =
      a.balance == b.balance
  }

  implicit val animalInstance = new Equal[Animal] {
    override def equal(a: Animal, b: Animal): Boolean =
      a.name == b.name && a.wild == b.wild
  }

  implicit val personEqual = new Equal[Person] {
    override def equal(a: Person, b: Person): Boolean =
      a.name == b.name && a.email == b.email
  }
}

object AnotherPersonEqual {
  implicit val anotherPersonEqual = new Equal[Person] {
    override def equal(a: Person, b: Person): Boolean = a.email == b.email
  }
}

object Equality extends App {

  println("Equality")

  def equalityOfPerson(personA: Person, personB: Person)(implicit
      personEquality: Equal[Person]
  ): Boolean =
    personEquality.equal(personA, personB)

  val donaldDuck = Person("Donald Duck", "donald@duck.com")
  val mickeyMouse = Person("Mickey Mouse", "mickey@disneyismine.come")
  val donaldDuckDouble = Person("Donald Duck", "donald@duck.com")
  val donaldDuckWithDifferentName = Person("Donald no Duck", "donald@duck.com")
  import AnotherPersonEqual.anotherPersonEqual

  println(
    s"Donald and Mickey should not be the same -> ${equalityOfPerson(donaldDuck, mickeyMouse)}"
  )
  println(equalityOfPerson(donaldDuck, donaldDuckDouble))
  println(
    equalityOfPerson(donaldDuckWithDifferentName, donaldDuckDouble)
  ) // <--
  println(Equal[Person].equal(donaldDuckDouble, donaldDuckWithDifferentName))

  println("Eq" + Eq(donaldDuck, donaldDuckDouble))
//  println("Eq" + Eq(donaldDuck, donaldDuckDouble)

  val card1 = CreditCard(100)
  val card2 = CreditCard(100)

  case class Animal(name: String, wild: Boolean)

  private val animal1 = Animal("Lion", wild = true)
  val animal3 = Animal("Lion", wild = true)
  private val animal2 = Animal("Dog", wild = false)

  Eq(card1, card2)
  println(Eq.equals(card1, card2))
  println(Eq.equals(animal1, animal2))

}
