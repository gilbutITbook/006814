/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package scratch;

import iloveyouboss.*;
import org.junit.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class BearingTest {
   @Test(expected=BearingOutOfRangeException.class)
   public void throwsOnNegativeNumber() {
      new Bearing(-1);
   }

   @Test(expected=BearingOutOfRangeException.class)
   public void throwsWhenBearingTooLarge() {
      new Bearing(Bearing.MAX + 1);
   }
   
   @Test
   public void answersValidBearing() {
      assertThat(new Bearing(Bearing.MAX).value(), equalTo(Bearing.MAX));
   }
   
   @Test
   public void answersAngleBetweenItAndAnotherBearing() {
      assertThat(new Bearing(15).angleBetween(new Bearing(12)), equalTo(3));
   }
   
   @Test
   public void angleBetweenIsNegativeWhenThisBearingSmaller() {
      assertThat(new Bearing(12).angleBetween(new Bearing(15)), equalTo(-3));
   }
}
