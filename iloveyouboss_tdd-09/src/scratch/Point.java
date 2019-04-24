/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package scratch;

public class Point {
   public final double x;
   public final double y;

   public Point(double x, double y) {
      this.x = x;
      this.y = y;
   }
   
   @Override
   public String toString() {
      return String.format("(%s, %s)", x, y);
   }
}
