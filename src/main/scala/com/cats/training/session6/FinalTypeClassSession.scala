package com.cats.training.session6

import cats.Monoid
import com.cats.training.Person
import com.cats.training.session2.XMLTypeClass.personAssimilation
import com.cats.training.session6.Enrichment.{MammalEnricher, ProductEnricher}
import com.cats.training.session6.XMLRenderer.productXmlRenderer

object FinalTypeClassSession extends App {
  println("FinalTypeClassSession")

  val simba = Mammal("Lion", 10, carnivorous = true)
  val kinderSurprise = Product("Kinder Surprise", 1.99, inStock = true)
  val kinderBueno = Product("Kinder Bueno", 2.99, inStock = true)

//  mammal.toXml

  implicit val mammalXmlRenderer: XMLRenderer[Mammal] =
    new XMLRenderer[Mammal] {
      override def toXml(m: Mammal): String =
        s"""
         |<Mammal>
         |  <Name>${m.name}</Name>
         |  <Age>${m.age}</Age>
         |  <Carnivorous>${m.carnivorous}</Carnivorous>
         |</Mammal>
         |""".stripMargin
    }

//  def xmlify[A](data: A)(implicit renderer: XMLRenderer[A]): String = {
//    renderer.toXml(data)
//  }

  def productXMLIfy(
      product: Product
  )(implicit renderer: XMLRenderer[Product]): String = {
    renderer.toXml(product)
  }

  def mammalXMLify(
      mammal: Mammal
  )(implicit renderer: XMLRenderer[Mammal]): String = {
    renderer.toXml(mammal)
  }

//  println(productXMLIfy(kinderSurprise))
//  println(productXMLIfy(kinderBueno))
//  println(mammalXMLify(simba))
  XMLer(simba)

  println(XMLWriterInstances.toXML(simba))

  XMLRenderer[Product].toXml(kinderBueno)

  println(XMLer(Monoid[Product].combine(kinderBueno, kinderSurprise)))

  kinderBueno.toXml

  simba.toXml

  val hondaCivic = Vehicle("Honda", "Civic")

import Enrichment.VehicleSyntax
import XMLWriterInstances.vehicleXmlRenderer

  hondaCivic.toXml

}
