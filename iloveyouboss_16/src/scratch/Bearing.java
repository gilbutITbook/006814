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

public class Bearing {
   public static final int MAX = 359;
   private int value;

   public Bearing(int value) {
      if (value < 0 || value > MAX) throw new BearingOutOfRangeException();
      this.value = value;
   }

   public int value() { return value; }

   public int angleBetween(Bearing bearing) { return value - bearing.value; }
}
