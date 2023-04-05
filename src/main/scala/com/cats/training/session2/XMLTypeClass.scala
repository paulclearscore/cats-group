package com.cats.training.session2

import com.cats.training.Person
//import com.cats.training.session2.XMLTypeClass.personAssimilation

// type class
trait XMLRenderer[A] {
  def render(a: A): String
}

object XMLRenderer {
  // type class instance
  implicit val intRenderer = new XMLRenderer[Int] {
    override def render(a: Int): String = s"<Int>$a</Int>"
  }

  // THINK I LOST EVERYONE!
  // We are here when you get back -- cool I can carry oh in silence LOVE IT

  implicit val cStringRenderer = new XMLRenderer[String] {
    override def render(string: String): String = s"\"$string\""
  }
//  implicit val otherPersonRenderer = new XMLRenderer[Person] {
//    override def render(person: Person): String =
//      s"""
//    |<Person>
//   |  <Name>${render(person.name)}</Name>
//   |  <Email>${person.email.render()}</Email>
//   |</Person>
//   |""".stripMargin
//  }
}

object GGXML {
  implicit val personRender = new XMLRenderer[Person] {
    override def render(person: Person): String =
      s"""
   |<Person>
   |  <Name>${person.name}</Name>
   |  <Email>${person.email}</Email>
   |  </Person>
   |""".stripMargin
  }
}

// implicit wrapper
object XMLOps {
  // type class instance
  implicit val daniloPersonRender = new XMLRenderer[Person] {
    override def render(person: Person): String =
      s"""
   |<Person>
   |  <Name>
   |    ${person.name}
   |  </Name>
   |  <Email>
   |    ${person.email}
   |  </Email>
   |  </Person>
   |""".stripMargin
  }
}
object XMLTypeClass extends App {

  // type class interface
  private def convertInt(
      a: Int
  )(implicit renderer: XMLRenderer[Int]): String = {
    renderer.render(a)
  }

  private def convertHumans(p: Person)(implicit renderer: XMLRenderer[Person]): String = {
    renderer.render(p)
  }

  //  implicit val lowerIntRenderer = new XMLRenderer[Int] {
  //    override def render(a: Int): String = s"<integer>$a</integer>"
  //  }

  val person1 = Person("Sally", "sally@sally.com")
  val person2 = Person("Donald", "don@sally.com")

  import GGXML.personRender
//  println(convertInt(566))

  println(
  convertHumans(person1))

  // using a TypeClass from Cats Library

    import cats.Monoid
  //



    implicit val personAssimilation: Monoid[Person] = new Monoid[Person] {
      override def empty: Person = Person("", "")

      override def combine(x: Person, y: Person): Person = {
        Person(
          x.name + y.name,
          x.email + y.email
        )
      }
    }
  //
  println(
    Monoid.combine(person1, person2))
  //  person1.combine(person2)

}
