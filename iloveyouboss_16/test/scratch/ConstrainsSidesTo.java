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

public class ConstrainsSidesTo extends TypeSafeMatcher<Rectangle> {
   private int length;

   public ConstrainsSidesTo(int length) {
      this.length = length;
   }

   @Override
   public void describeTo(Description description) {
      description.appendText("both sides must be <= " + length);
   }

    
   @Override
   protected boolean matchesSafely(Rectangle rect) {
      return 
        Math.abs(rect.origin().x - rect.opposite().x) <= length &&
        Math.abs(rect.origin().y - rect.opposite().y) <= length;
   }
   
   @Factory
   public static <T> Matcher<Rectangle> constrainsSidesTo(int length) {
      return new ConstrainsSidesTo(length);
   }
}
