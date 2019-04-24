/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package transmission;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TransmissionTest {
   private Transmission transmission;
   private Car car;
   
   @Before
   public void create() {
      car = new Car();
      transmission = new Transmission(car);
   }

   @Test
   public void remainsInDriveAfterAcceleration() {
      transmission.shift(Gear.DRIVE);
      car.accelerateTo(35);
      assertThat(transmission.getGear(), equalTo(Gear.DRIVE));
   }
   
   @Test
   public void ignoresShiftToParkWhileInDrive() {
      transmission.shift(Gear.DRIVE);
      car.accelerateTo(30);

      transmission.shift(Gear.PARK);

      assertThat(transmission.getGear(), equalTo(Gear.DRIVE));
   }
   
   @Test
   public void allowsShiftToParkWhenNotMoving() {
      transmission.shift(Gear.DRIVE);
      car.accelerateTo(30);
      car.brakeToStop();
      
      transmission.shift(Gear.PARK);
      
      assertThat(transmission.getGear(), equalTo(Gear.PARK));
   }
}
