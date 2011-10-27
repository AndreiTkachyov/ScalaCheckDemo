package demo.ex01

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

@RunWith(classOf[JUnitRunner])
class SimpleFuncUtilScalaTest extends FlatSpec with ShouldMatchers {

  val f = new SimpleFuncUtil

  "Function" should "behave correctly" in {
    f.func1(0) == 1
    f.func1(0) === 1
    f.func1(0) should be (1)
  }

  //...
}