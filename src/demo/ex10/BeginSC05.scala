package demo.ex10

import org.scalacheck.Prop

object BeginSC05 extends App {

  override def main(args: Array[String]) {

    Prop.forAll((a: Int, b: Int) =>
      a + b == b + a
    ).check
  }
}