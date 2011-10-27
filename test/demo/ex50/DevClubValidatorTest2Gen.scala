package demo.ex50

import org.scalatest.{FlatSpec, BeforeAndAfter}
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import java.util.Calendar
import org.scalatest.matchers.ShouldMatchers
import org.scalacheck.Gen._
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.{Prop, Gen}
import org.scalacheck.Test.Params

@RunWith(classOf[JUnitRunner])
class DevClubValidatorTest2Gen extends FlatSpec with BeforeAndAfter with ShouldMatchers {

  var validator: DevClubValidator = _

  val alphaNumStr = for(cs <- listOf(Gen.alphaNumChar)) yield cs.mkString

  //creating own Calendar generator
  val calGen = for {
    year <- Gen.choose(1900, 2011)
    month <- Gen.choose(0, 11)  // 0 = JAN, 11 = DEC
    date <- Gen.choose(1, 28)
  } yield {
    val cal = Calendar.getInstance()
    cal.set(year, month, date)
    cal
  }

  //creating own User object generator
  val userGen = for {
    name <- alphaNumStr
    email <- alphaNumStr
    age <- arbitrary[Int]
    lovesBeer <- arbitrary[Boolean]
    date <- calGen
  } yield new User(name, email, age, lovesBeer, date)


  before {
    validator = new DevClubValidator
  }

  "User" should "be invalid on absolutely random data (deliberately)" in {
    Prop.forAll(userGen) {
      (u: User) => Prop.collect(u) {
        validator.isValidUser(u) == true
      }
    }.check(Params(minSuccessfulTests = 10, maxSize = 15))

  }

}
