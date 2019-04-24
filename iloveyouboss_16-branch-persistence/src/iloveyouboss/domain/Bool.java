/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package iloveyouboss.domain;

public enum Bool {
   False(0),
   True(1);
   public static final int FALSE = 0;
   public static final int TRUE = 1;
   private int value;
   private Bool(int value) { this.value = value; }
   public int getValue() { return value; }
}
