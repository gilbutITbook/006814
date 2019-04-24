/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package scratch;

import org.junit.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.hamcrest.number.IsCloseTo.*;
// ...

public class AssertHamcrestTest {
   @Test
   @Ignore
   @ExpectToFail
   public void assertDoublesEqual() {
      assertThat(2.32 * 3, equalTo(6.96));
   }
   
   @Test
   public void assertWithTolerance() {
      assertTrue(Math.abs((2.32 * 3) - 6.96) < 0.0005);
   }
   
   @Test
   public void assertDoublesCloseTo() {
      assertThat(2.32 * 3, closeTo(6.96, 0.0005));
   }
}
