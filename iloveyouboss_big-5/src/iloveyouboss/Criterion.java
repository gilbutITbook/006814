/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package iloveyouboss;

public class Criterion implements Scoreable {
   // ... 
   private Weight weight;
   private Answer answer;
   private int score;

   public Criterion(Answer answer, Weight weight) {
      this.answer = answer;
      this.weight = weight;
   }
   
   public Answer getAnswer() { return answer; }
   public Weight getWeight() { return weight; }
   
   public void setScore(int score) { this.score = score; }
   public int getScore() { return score; }

   public boolean matches(Answer answer) {
      return getWeight() == Weight.DontCare ||
             answer.match(getAnswer());
   }
}
