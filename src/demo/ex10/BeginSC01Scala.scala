package demo.ex10

import org.scalacheck.Prop

object BeginSC01Scala extends App {

  override def main(args: Array[String]) {

    Prop.forAll {               // forAll - is a higher order function!
      (n: Int) => {
        java.lang.Math.abs(n) >= 0
      }
    }.check
  }
}