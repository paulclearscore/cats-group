package com.cats.training.session2

final case class CreditCard(balance: Int)
object CreditCard {
  implicit val balanceOrder =
    Ordering.fromLessThan[CreditCard]((x, y) => x.balance < y.balance)
}
