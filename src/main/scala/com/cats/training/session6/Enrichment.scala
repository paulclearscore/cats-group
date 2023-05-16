package com.cats.training.session6

object Enrichment {


  implicit class ProductEnricher(product: Product) {
    def toXml(implicit
        renderer: XMLRenderer[Product]
    ): String =
      renderer.toXml(product)
  }

  implicit class AnEnricher[A](a: A) {
    def toXml(renderer: XMLRenderer[A]): String =
      renderer.toXml(a)
  }

  implicit class MammalEnricher(mammal: Mammal) {
    def toXml(implicit renderer: XMLRenderer[Mammal]): String =
      renderer.toXml(mammal)
  }

  implicit class VehicleSyntax(vehicle: Vehicle) {
    def toXml(implicit renderer: XMLRenderer[Vehicle]): String =
      renderer.toXml(vehicle)
  }


}


//def toXml = {
//  println(
//    s"""
//       |<Vehicle>
//       |  <Make>${vehicle.make}</Make>
//       |  <Colour>${vehicle.colour}</Colour>
//       |</Vehicle>
//       |""".stripMargin)
//}