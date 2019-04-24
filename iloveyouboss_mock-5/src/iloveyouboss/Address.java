/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package iloveyouboss;

public class Address {
   public final String road;
   public final String city;
   public final String state;
   public final String zip;
   public final String houseNumber;

   public Address(String houseNumber, String road, String city, String state, String zip) {
      this.houseNumber = houseNumber;
      this.road = road;
      this.city = city;
      this.state = state;
      this.zip = zip;
   }
   
   @Override
   public String toString() {
      return houseNumber + " " + road + ", " + city + " " + state + " " + zip;
   }
}
