package com.cats.training.session7

import cats.Eq
import cats.syntax.eq._
import cats.instances.int._
import cats.instances.option._
import cats.instances.string._
import cats.implicits._
import cats.syntax.option._
import com.cats.training.session3.implClasses.StringEqQ

object EqCats extends App {

  // error
  List(1, 2, 3).map(Option(_)).filter(item => item == 1)

  // better
  List(1, 2, 3).map(Option(_)).filter(item => item.contains(1))

  // Cats
//  List(1, 2, 3).map(Option(_)).filter(item => item === Some(1))

  val eqInt = Eq[Int]

  println(eqInt.eqv(123, 123))
  println(eqInt.eqv(123, 234))
  println(eqInt.neqv(123, 234))
//  println(123 === 123)
//  println(123 =!= 128)

//  (Some("Numpty") : Option[String]) === (None : Option[String])

}
