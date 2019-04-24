/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package iloveyouboss;

import java.util.*;

public class MatchSet {
   private Map<String, Answer> answers;
   private int score = 0;

   public MatchSet(Map<String, Answer> answers, Criteria criteria) {
      this.answers = answers;
      calculateScore(criteria);
   }

   private void calculateScore(Criteria criteria) {
      for (Criterion criterion: criteria) 
         if (criterion.matches(answerMatching(criterion))) 
            score += criterion.getWeight().getValue();
   }

   private Answer answerMatching(Criterion criterion) {
      return answers.get(criterion.getAnswer().getQuestionText());
   }

   public int getScore() {
      return score;
   }
}
