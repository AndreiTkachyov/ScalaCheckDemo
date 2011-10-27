package demo.ex50

import org.scalatest.{FlatSpec, BeforeAndAfter}
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import java.util.Calendar
import org.scalatest.matchers.ShouldMatchers
import org.scalacheck.Gen._
import org.scalacheck.Gen

@RunWith(classOf[JUnitRunner])
class DevClubValidatorTest extends FlatSpec with BeforeAndAfter with ShouldMatchers {

  var validator: DevClubValidator = _

  val alphaNumStr = for(cs <- listOf(Gen.alphaNumChar)) yield cs.mkString

  before {
    validator = new DevClubValidator
  }

  "User" should "be valid user" in {

    val date = Calendar.getInstance()
    date.set(2011, 03, 21)

    val user = User("Vasja Pupkin",
                    "vasja.pupkin@email.net",
                    20,
                    true,
                    date)
    validator.isValidUser(user) should be (true)
  }

  "Yound user" should "be invalidated" in {
    val date = Calendar.getInstance()
    date.set(2011, 03, 21)

    val user = User("Fedja Pupkin",
                    "fedja.pupkin@email.net",
                    15, //!
                    true,
                    date)

    evaluating {
      validator.isValidUserThrowsExceptions(user)
    } should produce [IllegalArgumentException]
  }

  "Beer hater" should "not be allowed!" in {
    val date = Calendar.getInstance()
    date.set(2011, 03, 21)

    val user = User("Fedja Pupkin",
                    "fedja.pupkin@email.net",
                    25,
                    false,  //!
                    date)

    evaluating {
      validator.isValidUserThrowsExceptions(user)
    } should produce [IllegalArgumentException]
  }

}
