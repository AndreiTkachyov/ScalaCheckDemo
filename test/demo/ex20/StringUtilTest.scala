package demo.ex20

import org.scalacheck.Prop
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalacheck.Test.Params
import org.scalatest.{FlatSpec, BeforeAndAfter}

@RunWith(classOf[JUnitRunner])
class StringUtilTest extends FlatSpec with BeforeAndAfter {

  var sc: StringUtil = _

  before {
    sc = new StringUtil()
  }

  "res string" should "have length equal to sum of 3 input strings sizes" in {
    val str1 = "DevClub"
    val str2 = " will RULE"
    val str3 = " the World!"

    val res = sc.concat(str1, str2, str3)
    println(res)
    println()

    assert(res.length() == str1.length() + str2.length() + str3.length)
    assert(res.startsWith(str1))
    assert(res.endsWith(str3))
  }

  "res string" should "have length equal to sum of 3 strings sizes FOR ALL inputs" in {
    Prop.forAll {
      (s1: String, s2: String, s3: String) => Prop.collect(s1, s2, s3) {
        s1.length() + s2.length() + s3.length() == sc.concat(s1, s2, s3).length()
      }
    }.check(Params(minSuccessfulTests = 10, maxSize = 20, workers = 4))
  }

  "res string" should "start with s1 FOR ALL inputs" in {
    Prop.forAll {
      (s1: String, s2: String, s3: String) => {
        sc.concat(s1, s2, s3).startsWith(s1)
      }
    }.check(Params(minSuccessfulTests = 10))
  }

  "res string" should "end with s3 FOR ALL inputs" in {
    Prop.forAll {
      (s1: String, s2: String, s3: String) => {
        sc.concat(s1, s2, s3).endsWith(s3)
      }
    }.check(Params(minSuccessfulTests = 10))
  }

}
