/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package iloveyouboss;

public enum Weight {
   MustMatch(Integer.MAX_VALUE),
   VeryImportant(5000),
   Important(1000),
   WouldPrefer(100),
   DontCare(0);
   
   private int value;

   Weight(int value) { this.value = value; }
   public int getValue() { return value; }
}
