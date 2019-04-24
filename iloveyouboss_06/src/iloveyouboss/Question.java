/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package iloveyouboss;

public abstract class Question {
   private String text;
   private String[] answerChoices;
   private int id;

   public Question(int id, String text, String[] answerChoices) {
      this.id = id;
      this.text = text;
      this.answerChoices = answerChoices;
   }
   
   public String getText() {
      return text;
   }
   
   public String getAnswerChoice(int i) {
      return answerChoices[i];
   }

   public boolean match(Answer answer) {
      return false;
   }

   abstract public boolean match(int expected, int actual);

   public int indexOf(String matchingAnswerChoice) {
      for (int i = 0; i < answerChoices.length; i++)
         if (answerChoices[i].equals(matchingAnswerChoice))
            return i;
      return -1;
   }
}
