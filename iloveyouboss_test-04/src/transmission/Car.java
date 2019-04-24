/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package transmission;

public class Car implements Moveable {

   private int mph;

   @Override
   public int currentSpeedInMph() {
      return mph;
   }

   public void accelerateTo(int mph) {
      this.mph = mph;
   }

   public void brakeToStop() {
      mph = 0;
   }
}
