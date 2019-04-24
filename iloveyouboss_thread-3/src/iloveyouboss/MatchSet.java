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

public class MatchSet implements Comparable<MatchSet> {
   private Map<String, Answer> answers;
   private Criteria criteria;
   private int score = Integer.MIN_VALUE;
   private String profileId;

   public MatchSet(String profileId, Map<String, Answer> answers, Criteria criteria) {
      this.profileId = profileId;
      this.answers = answers;
      this.criteria = criteria;
   }
   
   public String getProfileId() {
      return profileId;
   }

   public int getScore() {
      if (score == Integer.MIN_VALUE) calculateScore();
      return score;
   }
   
   private void calculateScore() {
      score = 0;
      for (Criterion criterion: criteria) 
         if (criterion.matches(answerMatching(criterion))) 
            score += criterion.getWeight().getValue();
   }

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

   @Override
   public int compareTo(MatchSet that) {
      return new Integer(getScore()).compareTo(new Integer(that.getScore()));
   }

   public Map<String, Answer> getAnswers() {
      return answers;
   }

   public Criteria getCriteria() {
      return criteria;
   }
}
