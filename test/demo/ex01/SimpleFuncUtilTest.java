package demo.ex01;

import junit.framework.TestCase;
import org.junit.Test;

public class SimpleFuncUtilTest extends TestCase {

  SimpleFuncUtil f = new SimpleFuncUtil();

  @Test //zero
  public void test0() throws Exception {
    assertEquals(1, f.func1(0));
  }

  @Test //positive
  public void test100() throws Exception {
    assertEquals (101, f.func1(100));
  }

  @Test //negative
  public void testNegative() throws Exception {
    assertEquals (6, f.func1(-5));
  }

     // ...
     // ...

}
