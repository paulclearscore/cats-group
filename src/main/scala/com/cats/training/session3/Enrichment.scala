package com.cats.training.session3

import com.cats.training.{Loan, Person}
import com.cats.training.session2.{CreditCard, Equal, XMLRenderer}
import com.cats.training.session3.implClasses._

object Enrichment extends App {
  println("Enrichment")
//  import com.cats.training.session2.XMLOps._
  import com.cats.training.session2.GGXML._

  println("the quick brown fox".numberOfVowels)

  val paulsCard = CreditCard(457)

  println(paulsCard.youFellOnHardTimes)
  println(paulsCard.youGotRich)
  println(Rich(paulsCard).youGotEvenRicher)

  CreditCard(700).youGotRich

  println(Person("Dan", "dan@gmail.com").toXml)
  println(CreditCard(788).toXml)

  println("Drinking the Kool Aid")
//  println(2.yeah())
//  println(3.yeah())
//  println(66.yeah())
//  println((-1).yeah())
  3.times(i => println(s"Look - it's the number $i!"))
  println("abcd".===("ABCD"))
  println(Loan(1, 1).calculateInterest)

}

object implClasses {

  implicit class LoanInterest(data: Loan) {
    def calculateInterest: Double = {
      data.amount * 1.25
    }
  }
//  implicit class HtmlOps[T](data: T) {
//    def toHtml(implicit writer: HtmlWriter[T]) =
//      writer.toHtml(data)
//  }

  implicit class StringEqQ[T](a: T) {
    def ===(otherThing: T)(implicit strEq: Equal[T]): Boolean = {
      strEq.equal(a, otherThing)
    }
  }

//  implicit class StringEq(in: String) {
//    def ===(otherString: String) = {
//      in.equals(otherString)
//    }
//  }

  implicit class JsonOps[T](data: T) {
    def toJson(implicit writer: JsonWriter[T]) =
      writer.write(data)
  }

  implicit class YeahYeah(num: Int) {
    def times(f: Int => Unit) = {
      for { _ <- 0 until num } f(num)

    }
    def yeah(): Unit = {
//      if (num >= 0) println("Oh yeah!\n" * num) else ()
      for { _ <- 0 until num } println("Oh yeah!")

//      def loop(index: Int = 0): Unit = {
//        if (index == num) ()
//        else {
//          println("yeah!")
//          loop(index + 1)
//        }
//      }
//      loop()
//      for {
//        _ <- num println("yeah!")
//      }yield ()

    }
  }

  implicit class ExtraStringMethods(str: String) {
    val vowels = Seq('a', 'e', 'i', 'o', 'u')

    def numberOfVowels =
      str.toList.filter(vowels contains _).length
  }

  implicit class Rich(creditCard: CreditCard) {
    def youGotRich: Int =
      creditCard.balance * 1000000

    def youFellOnHardTimes: Int =
      5
  }

  implicit class XmlRenderer[T](a: T) {
    def toXml(implicit renderer: XMLRenderer[T]): String = {
      renderer.render(a)
    }
  }

  implicit class Richer(rich: Rich) {
    def youGotEvenRicher: Int =
      rich.youGotRich
  }

  implicit class DaniloJsonOps[T](data: T) {
    def toJson(implicit writer: JsonWriter[T]): Json = {
      writer.write(data)
    }
  }
}
