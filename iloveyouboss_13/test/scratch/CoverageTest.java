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

public class CoverageTest {
   @Test
   public void noIncrementOfCount() {
      new Coverage().soleMethod();
   }
   
   @Test
   public void incrementOfCount() {
      Coverage c = new Coverage();
      c.count = 1;
      c.soleMethod();
   }
}
