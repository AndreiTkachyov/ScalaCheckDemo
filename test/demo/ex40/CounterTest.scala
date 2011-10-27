package demo.ex40

import org.scalatest.FlatSpec
import org.scalatest.prop.PropertyChecks
import org.scalatest.matchers.ShouldMatchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CounterTest extends FlatSpec with PropertyChecks with ShouldMatchers {

  "Counter" should "have correct states" in {
    val stateTransitions =
      Table(
        ("actionZ", "expectedCount"),  //1st row is a heading
        (Reset,    0),  // Types are: (Action, Int)
        (Click,    1),  // ...
        (Click,    2),
        (Click,    3),
        (Enter(5), 5),
        (Click,    6),
        (Enter(1), 1),
        (Click,    2),
        (Click,    3),
        (Reset,    0)
      )

    val counter = new Counter
    forAll(stateTransitions) {
      (action, expectedCount) =>
        action match {
          case Reset => counter.reset()
          case Click => counter.click()
          case Enter(n) => counter.enter(n)
        }
        counter.count should equal(expectedCount)
    }
  }
}

abstract class Action
case object Reset extends Action
case object Click extends Action
case class Enter(n: Int) extends Action