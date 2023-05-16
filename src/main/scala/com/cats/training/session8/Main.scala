package com.cats.training.session8
import cats.Eq
import cats.instances.string._
import cats.instances.option._
import cats.syntax.eq._
import com.cats.training.{Animal, Cat, Dinosaur}
import com.cats.training.Animal._

object Main extends App {

  val cat1 = Cat("Harv", 23, "brown")
  val cat2 = Cat("Paul", 44, "ginger")
  val cat3 = Cat("Harv", 23, "granite")

  println(cat2 === cat1)
  println(cat1 === cat3)

  val animal : Animal = Dinosaur("garfield", 78329, true)
  val barney = Dinosaur("garfield", 78329, true)

  println(animal === barney)

  trait AnimalCage[+T]

  val mySome = Some(5)
  val myNone = Option.empty

  (mySome: Option[Int]) === (myNone)

}
