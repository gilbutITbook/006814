/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package iloveyouboss;

public class PercentileQuestion extends Question {
   public PercentileQuestion(int id, String text, String[] answerChoices) {
      super(id, text, answerChoices);
   }

   @Override
   public boolean match(int expected, int actual) {
      return expected <= actual;
   }
}
