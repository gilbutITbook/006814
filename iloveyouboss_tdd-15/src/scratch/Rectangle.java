/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package scratch;

public class Rectangle {
   private Point origin;
   private Point opposite;

   public Rectangle(Point origin, Point oppositeCorner) {
      this.origin = origin;
      this.opposite = oppositeCorner;
   }

   public Rectangle(Point origin) {
      this.opposite = this.origin = origin;
   }

   public int area() {
      return (int)(Math.abs(origin.x - opposite.x) *
            Math.abs(origin.y - opposite.y));
   }

   public void setOppositeCorner(Point opposite) {
      this.opposite = opposite;
   }

   public Point origin() {
      return origin;
   }

   public Point opposite() {
      return opposite;
   }
   
   @Override
   public String toString() {
      return "Rectangle(origin " + origin + " opposite " + opposite + ")";
   }
}
