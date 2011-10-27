package demo.ex20

import org.scalacheck.Prop
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalacheck.Test.Params
import org.scalatest.{FlatSpec, BeforeAndAfter}

@RunWith(classOf[JUnitRunner])
class StringUtilTestMultiProp extends FlatSpec with BeforeAndAfter {

  var sc: StringUtil = _

  val propEqSize = Prop.forAll {
    (s1: String, s2: String, s3: String) => {
      s1.length() + s2.length() + s3.length() == sc.concat(s1, s2, s3).length()
    }
  }

  val propStartsWith = Prop.forAll {
    (s1: String, s2: String, s3: String) => {
      sc.concat(s1, s2, s3).startsWith(s1)
    }
  }

  val propEndsWith = Prop.forAll {
    (s1: String, s2: String, s3: String) => {
      sc.concat(s1, s2, s3).endsWith(s3)
    }
  }

  val falseProp = Prop.forAll {
    (s1: String, s2: String, s3: String) => {
      sc.concat(s1, s2, s3).equalsIgnoreCase(s3)
    }
  }

  before {
    sc = new StringUtil()
  }

  "res string" should "hold all 3 properties !" in {
    (propEqSize && propStartsWith && propEndsWith /*&& falseProp*/).check(Params(minSuccessfulTests = 30, workers = 3))
  }
}
