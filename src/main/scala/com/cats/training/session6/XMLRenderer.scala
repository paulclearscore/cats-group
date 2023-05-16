package com.cats.training.session6

trait XMLRenderer[T] {
  def toXml(toConv: T): String
}

object XMLer {
  def apply[A](a: A)(implicit instance: XMLRenderer[A]): String =
    instance.toXml(a)
}

object XMLRenderer {

  implicit class MammalEnricher(mammal: Mammal) {
    def toXml(implicit renderer: XMLRenderer[Mammal]): String =
      renderer.toXml(mammal)
  }

  def apply[A](implicit instance: XMLRenderer[A]): XMLRenderer[A] =
    instance

  implicit val productXmlRenderer: XMLRenderer[Product] =
    new XMLRenderer[Product] {
      override def toXml(p: Product): String =
        s"""
           |<Product>
           | <Name>${p.name}</Name>
           | <Price>${p.price}</Price>
           | <inStock>${p.inStock}</inStock>
           |</Product>
           |""".stripMargin
    }

}

object XMLWriterInstances {

  implicit val vehicleXmlRenderer: XMLRenderer[Vehicle] =
    new XMLRenderer[Vehicle] {
      override def toXml(vehicle: Vehicle): String =
        s"""
           |<Vehicle>
           |  <Make>${vehicle.make}</Make>
           |  <Colour>${vehicle.colour}</Colour>
           |</Vehicle>
           |""".stripMargin
    }

  def toXML[A](a: A)(implicit renderer: XMLRenderer[A]) = renderer.toXml(a)

  implicit val productXmlRenderer3: XMLRenderer[Product] =
    new XMLRenderer[Product] {
      override def toXml(p: Product): String =
        s"""
           |<Product>
           | <inStock>${p.inStock}</inStock>
           |</Product>
           |""".stripMargin
    }

}
