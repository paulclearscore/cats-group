package com.cats.training

object OrderOrderings {

  implicit val quantityOrdering =
    Ordering.fromLessThan[Order]((ord1, ord2) => ord1.units < ord2.units)

  implicit val unitPriceOrdering = Ordering.fromLessThan[Order]((ord1, ord2) =>
    ord1.unitPrice < ord2.unitPrice
  )
}
