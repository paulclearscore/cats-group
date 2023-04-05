package com.cats.training

final case class Order(units: Int, unitPrice: Double) {
  val totalPrice: Double = units * unitPrice
}

object Order {
  implicit val totalPriceOrdering = Ordering.fromLessThan[Order]((ord1, ord2) =>
    ord1.totalPrice < ord2.totalPrice
  )
}
