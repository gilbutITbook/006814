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
import static org.junit.Assert.*;
import static org.hamcrest.number.IsCloseTo.*;
import static java.lang.Math.abs;

public class NewtonTest {
   static class Newton {
      private static final double TOLERANCE = 1E-16;

      public static double squareRoot(double n) {
         double approx = n;
         while (abs(approx - n / approx) > TOLERANCE * approx)
            approx = (n / approx + approx) / 2.0;
         return approx;
      }
   }

   @Test
   public void squareRoot() {
      double result = Newton.squareRoot(250.0);
      assertThat(result * result, closeTo(250.0, Newton.TOLERANCE));
   }
   
   @Test
   public void squareRootVerifiedUsingLibrary() {
      assertThat(Newton.squareRoot(1969.0), 
            closeTo(Math.sqrt(1969.0), Newton.TOLERANCE));
   }
}
