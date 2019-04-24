/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package transmission;

public class Transmission {

   private Gear gear;
   private Moveable moveable;

   public Transmission(Moveable moveable) {
      this.moveable = moveable;
   }

   public void shift(Gear gear) {
      // begs for a state-machine implementation
      if (moveable.currentSpeedInMph() > 0 && gear == Gear.PARK) return; 
      this.gear = gear;
   }

   public Gear getGear() {
      return gear;
   }

}
