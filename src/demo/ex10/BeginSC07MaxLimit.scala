package demo.ex10

import org.scalacheck.{Test, Prop}

object BeginSC07MaxLimit extends App {

  override def main(args: Array[String]) {

    Prop.forAll((a: Int, b: Int) => Prop.collect(a, b) {
      a + b == b + a
    }).check(Test.Params(minSuccessfulTests = 10))
  }
} //demo 2+ times