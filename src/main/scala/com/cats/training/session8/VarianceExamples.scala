package com.cats.training.session2

import cats.Eq
import cats.syntax.eq._
import cats.instances.string._
import cats.instances.int._
import cats.instances.list._
import com.cats.training.{Animal, Cat, Dinosaur}

object VarianceExamples extends App {
  // Rule of thumb:
  // "HAS a T" or "contains a T" = covariant
  // "ACTS on T" or "makes T ACT" = contravariant

  // How to remember covariant

  // Best example:
  // Covariance - A cage which can hold an animal, can also hold a cat! (Cat <: Animal) => Cage[Cat] <: Cage[Animal]
  class Cage[+T]
  val cage: Cage[Animal] = new Cage[Cat]
  val cage2: Cage[Animal] = new Cage[Dinosaur]

  // Contravariance - A Vet which can work on Animals, can work on my Cat. But a Vet[Cat], I wouldnt trust with my Animal
  class Vet[-T]
//  val vet1: Vet[Animal] = new Vet[Cat] /*  ❌Not allowed ❌ */
  val vet2: Vet[Cat] = new Vet[Animal] /*  ✅ Allowed ✅ because a vet that can work on all animals can work on a cat */
  val vet3: Vet[Dinosaur] = new Vet[Animal] // ✅


  val garfieldCat: Cat = new Cat("Garfield", 12, "orange and black")
  val garfieldDinosaur: Dinosaur = Dinosaur("Garfield", 42, isCarnivore = true)

  val animals: List[Animal] = List(garfieldCat, garfieldDinosaur)
  val cats: List[Cat] = List(garfieldCat, garfieldCat)

  trait JsonWriter[-A] {
    def write(value: A): String
  }

  val animalWriter: JsonWriter[Animal] = new JsonWriter[Animal] {
    override def write(animal: Animal): String =
      s"I am ${animal.name}."
  }
  val catWriter: JsonWriter[Cat] = new JsonWriter[Cat] {
    override def write(cat: Cat): String =
      s"I am ${cat.name} and I am ${cat.age} years old"
  }

  def format[A](value: A, writer: JsonWriter[A]): String =
    writer.write(value)

  val myAnimal: Animal = Cat("Monkey", 10, "orange")
  val myCat: Cat = Cat("Weirdo", 55, "ethereal")

  format(myAnimal, animalWriter)
//  format(myAnimal, catWriter)
  format(myCat, animalWriter)
  format(myCat, catWriter)

  // Contravariant typeclass - This typeclass SoundMaker will act on the inner type
  trait SoundMaker[-A]
  implicit object AnimalSoundMaker extends SoundMaker[Animal]
  implicit object DinosaurSoundMaker extends SoundMaker[Dinosaur]
  def makeSound[A](implicit soundMaker: SoundMaker[A]): Unit = println("hello")

  makeSound[Animal]
  makeSound[Cat]
  makeSound[Dinosaur](AnimalSoundMaker)

  implicit object OptionSoundMaker extends SoundMaker[Option[Int]]
  makeSound[Option[Int]]
  makeSound[Some[Int]]

  // Covariant - the animal show can be a show with only dogs, or cats+dogs, but a dog show cannot contain any Animal
  trait AnimalShow[+T] {
    def show: String
  }

  implicit object GeneralAnimalShow extends AnimalShow[Animal] {
    override def show: String = "animals doing a show"
  }

  implicit object CatsShow extends AnimalShow[Cat] {
    override def show: String = "Cats in a show!"
  }

  def organiseShow[T](implicit event: AnimalShow[T]): String = event.show

  println(organiseShow[Cat])
//  println(organiseShow[Dinosaur])
//  println(organiseShow[Animal])

}
