package com.cats.training

final case class Order(units: Int, unitPrice: Double) {
  val totalPrice: Double = units * unitPrice
}

object Order {
  implicit val totalPriceOrdering = Ordering.fromLessThan[Order]((ord1, ord2) =>
    ord1.totalPrice < ord2.totalPrice
  )
}

final case class Rational(numerator: Int, denominator: Int)
object Rational {
  implicit val rationalOrder = Ordering.fromLessThan[Rational]((x, y) => {
    val dec1: Double = (x.numerator.toDouble / x.denominator.toDouble)
    val dec2: Double = y.numerator.toDouble / y.denominator.toDouble
    dec1 < dec2
  })

}
final case class CreditCard(balance: Int)
object CreditCard {
  implicit val balanceOrder =
    Ordering.fromLessThan[CreditCard]((x, y) => x.balance < y.balance)
}

object RationalLessThanOrdering {
  implicit val orderingLess = Ordering.fromLessThan[Rational]((x, y) =>
    (x.numerator.toDouble / x.denominator.toDouble) <
      (y.numerator.toDouble / y.denominator.toDouble)
  )
}
object RationalGreaterThanOrdering {
  implicit val orderingMore = Ordering.fromLessThan[Rational]((x, y) =>
    (x.numerator.toDouble / x.denominator.toDouble) >
      (y.numerator.toDouble / y.denominator.toDouble)
  )
}

object Main extends App {

  val order1 = Order(units = 10, unitPrice = 1.50)
  val order2 = Order(units = 20, unitPrice = 3.50)
  val order3 = Order(units = 7, unitPrice = 1.20)

  val listOfOrders = List(order1, order3, order2)

  import OrderOrderings.quantityOrdering

  println(listOfOrders.sorted)

//  println("Miaow")

//  implicit val minOrdering = Ordering.fromLessThan[Int](_ < _)
//  implicit val maxOrdering = Ordering.fromLessThan[Int](_ > _)

  import MyContainer.absOrdering
//  val weirdlist = List(-4, -1, 0, 2, 3)
//  println(weirdlist.sorted)
//
//  val list = List(105, 3, 7, 88).sorted
//  println(list)

//  implicit val balanceOrder2 =
//    Ordering.fromLessThan[CreditCard]((x, y) => x.balance > y.balance)

  val monzoCard = CreditCard(8936763)
  val yonderCard = CreditCard(73773)
  val myCards = List[CreditCard](CreditCard(8936763), CreditCard(73773))
  println(myCards.sorted)

  val ratList = List(Rational(1, 2), Rational(3, 4), Rational(1, 3))

  import RationalGreaterThanOrdering.orderingMore
//  import RationalLessThanOrdering.orderingLess
//
//  val r1 = orderingMore
//  val r1 = orderingLess

  println(ratList.sorted)

  val practiseList = List(65, 800, 1, 6)

  implicit val aString: String = "some string"
  def aMethod(implicit astring: String) = {
    "blah blah" + astring
  }
//  println(aMethod)

}

object MyContainer {

  implicit val absOrdering =
    Ordering.fromLessThan[Int]((x, y) => Math.abs(x) < Math.abs(y))
}

//
//trait JsonWriter[A] {
//  def write(value: A): Json
//}
//
//sealed trait Json
//final case class JsObject(get: Map[String, Json]) extends Json
//final case class JsString(get: String) extends Json
//final case class JsNumber(get: Double) extends Json
//case object JsNull extends Json
//
//final case class Person(name: String, email: String)
//
//object JsonWriterInstances {
//  implicit val stringWriter: JsonWriter[String] =
//    new JsonWriter[String] {
//      def write(value: String): Json =
//        JsString(value)
//    }
//  implicit val personWriter: JsonWriter[Person] =
//    new JsonWriter[Person] {
//      def write(value: Person): Json =
//        JsObject(
//          Map(
//            "name" -> JsString(value.name),
//            "email" -> JsString(value.email)
//          )
//        )
//    }
//}
