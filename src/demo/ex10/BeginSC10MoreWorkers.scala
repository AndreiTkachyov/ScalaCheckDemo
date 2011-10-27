package demo.ex10

import org.scalacheck.{Test, Prop}

object BeginSC10MoreWorkers extends App {

  override def main(args: Array[String]) {

    // Multiply, Doubles
    val prop: Prop = Prop.forAll((a: Double, b: Double) => {
      a * b == b * a
    })

    //warm up JVM
    for (i <- 1 to 8) {
      test(prop = prop, workersNumber = i)
    }
    println("-------------------------")

    for (i <- 1 to 8) {
      val time = System.currentTimeMillis()
      test(prop = prop, workersNumber = i)
      println("[" + i + " worker(s)] completed in: " + (System.currentTimeMillis() - time) + "ms\n")
    }

  }

  def test(prop: Prop, workersNumber: Int) {
    prop.check(Test.Params(minSuccessfulTests = 75000, maxDiscardedTests = 2500000, workers = workersNumber))
  }
}