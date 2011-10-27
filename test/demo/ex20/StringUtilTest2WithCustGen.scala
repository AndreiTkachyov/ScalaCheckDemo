package demo.ex20

import org.scalacheck.Test.Params
import org.scalatest.{FlatSpec, BeforeAndAfter}
import org.scalacheck.{Gen, Prop}
import org.scalacheck.Gen._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class StringUtilTest2WithCustGen extends FlatSpec with BeforeAndAfter {

  var sc: StringUtil = _

  // Available gens: alphaChar, alphaStr, numChar, numStr, alphaNumChar, identifier, ..

  //val vowel = Gen.oneOf('A', 'E', 'I', 'O', 'U', 'Y')

  //val vowel: Gen[Char] = 'A' | 'E' | 'I' | 'O' | 'U' | 'Y'

  // Custom made gen:
  val alphaNumStr = for(cs <- listOf(Gen.alphaNumChar)) yield cs.mkString

  before {
    sc = new StringUtil()
  }

  "res string" should "have length equal to sum of 3 strings sizes FOR ALL inputs" in {
    Prop.forAll(alphaNumStr, alphaNumStr, alphaNumStr) {
      (s1: String, s2: String, s3: String) => Prop.collect(s1, s2, s3) {
        s1.length() + s2.length() + s3.length() == sc.concat(s1, s2, s3).length()
      }
    }.check(Params(minSuccessfulTests = 8, maxSize = 25, workers = 2))
  }

  "res string" should "start with s1 FOR ALL inputs" in {
    Prop.forAll(alphaStr, alphaNumStr, numStr) {
      (s1: String, s2: String, s3: String) => {
        sc.concat(s1, s2, s3).startsWith(s1)
      }
    }.check(Params(minSuccessfulTests = 10))
  }

  "res string" should "end with s3 FOR ALL inputs" in {
    Prop.forAll(numStr, alphaNumStr, alphaStr) {
      (s1: String, s2: String, s3: String) => {
        sc.concat(s1, s2, s3).endsWith(s3)
      }
    }.check(Params(minSuccessfulTests = 10))
  }

}
