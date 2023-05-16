package com.cats.training.session6

import cats.Monoid

case class Product(name: String, price: Double, inStock: Boolean) {}

object Product {
  implicit val productJoining: Monoid[Product] = new Monoid[Product] {
    override def empty: Product = Product(name = "", price = 0, inStock = true)

    override def combine(x: Product, y: Product): Product = Product(
      name = x.name ++ y.name,
      price = x.price + y.price,
      inStock = x.inStock && y.inStock
    )
  }

  implicit val productXmlRenderer2: XMLRenderer[Product] =
    new XMLRenderer[Product] {
      override def toXml(p: Product): String =
        s"""
           |<Product>
           | <Name>${p.name}</Name>
           | <inStock>${p.inStock}</inStock>
           |</Product>
           |""".stripMargin
    }

}
