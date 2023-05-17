package com.cats.training
import cats.Eq
import cats.syntax.eq._
import cats.instances.string._
import cats.instances.int._
import cats.instances.list._

trait Animal {
  def name: String
}

object Animal {
  implicit val animalEq: Eq[Animal] = Eq.instance[Animal] { (an1, an2) =>
    (an1.name === an2.name)
  }
}

case class Cat(name: String, age: Int, colour: String) extends Animal

object Cat {
  implicit val catEq: Eq[Cat] = Eq.instance[Cat] { (cat1, cat2) =>
    (cat1.name === cat2.name) &&
    (cat1.age === cat2.age) &&
    (cat1.colour === cat2.colour)
  }

}
case class Dinosaur(name: String, age: Int, isCarnivore: Boolean) extends Animal

//object Dinosaur {
//  implicit val dinoEq: Eq[Dinosaur] =
//    Eq.instance[Dinosaur] { (dino1, dino2) =>
//      dino1.name === dino2.name && dino1.age === dino2.age && dino1.isCarnivore && dino2.isCarnivore
//    }
//}
