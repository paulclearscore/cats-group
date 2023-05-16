package com.cats.training.session7

import java.util.Date

trait Printable[A] {

  def format(value: A): String

}

object PrintableInstances {

  implicit val stringFormat: Printable[String] = new Printable[String] {
    override def format(value: String): String = value
  }

  implicit val intFormat: Printable[Int] = new Printable[Int] {
    override def format(int: Int): String = int.toString
  }

  implicit val whiskers: Printable[Cat] = new Printable[Cat] {
    override def format(cat: Cat): String = {
      val name = Printable.format(cat.name)
      val age = Printable.format(cat.age)
      val color = Printable.format(cat.color)
      s"$name is a $age year-old $color cat."
    }
  }

  implicit val dateFormat: Printable[Date] = new Printable[Date] {
    override def format(date: Date): String = date.toString
  }
}

object Printable {

  def format[A](value: A)(implicit printable: Printable[A]): String = {
    printable.format(value)
  }

  def print[A](value: A)(implicit printable: Printable[A]): Unit = println(
    format(value)
  )
}

object PrintableSyntax {

  implicit class PrintableOps[A](item: A) {

    def format(implicit p: Printable[A]): String = p.format(item)

    def print(implicit p: Printable[A]): Unit = println(item.format)

  }
}
