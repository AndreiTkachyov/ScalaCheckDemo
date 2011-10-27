package demo.ex50

import org.scalatest.{FlatSpec, BeforeAndAfter}
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import java.util.Calendar
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.prop.TableDrivenPropertyChecks._

@RunWith(classOf[JUnitRunner])
class DevClubValidatorTest1TableProps extends FlatSpec with BeforeAndAfter with ShouldMatchers {

  var validator: DevClubValidator = _

  val date = Calendar.getInstance()
  date.set(2011, 03, 21)

  before {
    validator = new DevClubValidator
  }

  "User" should "be valid user" in {
    val validUsers =
      Table(
        ("name", "email", "age", "lovesBeer", "date"), //1st row is just a helping heading
        ("Vasja Pupkin", "vasja.p@email.net", 21, true, date),
        ("Fedja Pupkin", "fedja.p@email.net", 25, true, date),
        ("Felix X", "f.x@email.org", 44, true, date),
        ("Ars", "ars@hotmail.com", 30, true, date)
      )

    forAll (validUsers) { (name: String, email: String, age: Int, lovesBeer: Boolean, date: Calendar) =>
      validator.isValidUser(User(name, email, age, lovesBeer, date)) == true
    }

  }

  "User" should "be invalid user" in {
    val invalidUsers =
      Table(
        ("name", "email", "age", "lovesBeer", "date"), //1st row is just a helping heading
        ("V", "vasja.p@email.net", 21, true, date), // name.size < 3
        ("Fedja Pupkin", "fedja.", 25, true, date), // invalid email
        ("Felix X", "f.x@email.org", 10, true, date), // too young
        ("Mr.X", "mrx@hotmail.com", 30, false, date)  // Beer hater!!!
      )

    forAll (invalidUsers) { (name: String, email: String, age: Int, lovesBeer: Boolean, date: Calendar) =>
      evaluating {
        validator.isValidUserThrowsExceptions(User(name, email, age, lovesBeer, date))
      } should produce [IllegalArgumentException]
    }

  }

}
