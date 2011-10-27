package demo.ex10

import org.scalacheck.Prop

object BeginSC06Collect extends App {

  override def main(args: Array[String]) {

    Prop.forAll((a: Int, b: Int) => Prop.collect(a, b) {
      a + b == b + a
    }).check
  }
}