package demo.ex10

import java.lang.Math
import org.scalacheck.{Test, Gen, Prop}

object BeginSC21InvGenerators extends App {

  def square(n: Long) = n * n

  def squareRoot(n: Long) = Math.sqrt(n)

  override def main(args: Array[String]) {

    //val generator = Gen.choose(1L, 1000L) suchThat (_ % 2 == 0)
    val generator = Gen.choose(-100L, 100L) suchThat (_ > 0) suchThat (_ % 2 == 1)

    val prop = Prop.forAll(generator) {
      //Verifying that applying INVERSE function to function gives original value back
      (n: Long) => Prop.collect(n) {
        squareRoot(square(n)) == n
      }
    }
    prop.check(Test.Params(minSuccessfulTests = 10))

  }

}