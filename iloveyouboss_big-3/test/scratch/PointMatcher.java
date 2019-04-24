/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package scratch;

import org.hamcrest.*;

public class PointMatcher extends TypeSafeMatcher<Point> {
   private static final double TOLERANCE = 0.2;
   private double x;
   private double y;

   public PointMatcher(double x, double y) {
      this.x = x;
      this.y = y;
   }

   @Override
   public void describeTo(Description description) {
      description.appendText(String.format("a point near (%s, %s)", x, y));
   }

   @Override
   protected boolean matchesSafely(Point point) {
      return Math.abs(x - point.x) <= TOLERANCE && 
            Math.abs(y - point.y) <= TOLERANCE;
   }

   @Factory
   public static <T> Matcher<Point> isNear(double x, double y) {
      return new PointMatcher(x, y);
   }
}
