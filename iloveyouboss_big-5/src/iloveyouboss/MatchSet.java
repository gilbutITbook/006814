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
   // ...
   private Map<String, Answer> answers;
   private Criteria criteria;

   public MatchSet(Map<String, Answer> answers, Criteria criteria) {
      this.answers = answers;
      this.criteria = criteria;
   }

   public int getScore() {
      int score = 0;
      for (Criterion criterion: criteria) 
         if (criterion.matches(answerMatching(criterion))) 
            score += criterion.getWeight().getValue();
      return score;
   }
   // ...

   private Answer answerMatching(Criterion criterion) {
      return answers.get(criterion.getAnswer().getQuestionText());
   }

   public boolean matches() {
      if (doesNotMeetAnyMustMatchCriterion())
         return false;
      return anyMatches();
   }

   private boolean doesNotMeetAnyMustMatchCriterion() {
      for (Criterion criterion: criteria) {
         boolean match = criterion.matches(answerMatching(criterion));
         if (!match && criterion.getWeight() == Weight.MustMatch) 
            return true;
      }
      return false;
   }

   private boolean anyMatches() {
      boolean anyMatches = false;
      for (Criterion criterion: criteria) 
         anyMatches |= criterion.matches(answerMatching(criterion));
      return anyMatches;
   }
}
