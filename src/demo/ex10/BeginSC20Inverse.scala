package demo.ex10

import java.lang.Math
import org.scalacheck.{Gen, Prop}

object BeginSC20Inverse extends App {

  def square(n: Long) = n * n

  def squareRoot(n: Long) = Math.sqrt(n)

  override def main(args: Array[String]) {

    val prop = Prop.forAll {
      //Verifying that applying INVERSE function to function gives original value back
      (n: Long) =>
        squareRoot(square(n)) == n
    }
    prop.check

  }

}