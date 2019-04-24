/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package iloveyouboss;


public class BooleanQuestion extends Question {
   public BooleanQuestion(int id, String text) {
      super(id, text, new String[] { "No", "Yes" });
   }

   @Override
   public boolean match(int expected, int actual) {
      return expected == actual;
   }
}
