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
import java.util.function.*;
import java.util.stream.*;

public class Profile {

   private Map<String,Answer> answers = new HashMap<>();

   private int score;
   private String name;

   public Profile(String name) {
      this.name = name;
   }
   
   public String getName() {
      return name;
   }

   public void add(Answer answer) {
      answers.put(answer.getQuestionText(), answer);
   }

   public boolean matches(Criteria criteria) {
      calculateScore(criteria);
      if (doesNotMeetAnyMustMatchCriterion(criteria))
         return false;
      return anyMatches(criteria);
   }

   private boolean doesNotMeetAnyMustMatchCriterion(Criteria criteria) {
      for (Criterion criterion: criteria) {
         boolean match = criterion.matches(answerMatching(criterion));
         if (!match && criterion.getWeight() == Weight.MustMatch) 
            return true;
      }
      return false;
   }

   private void calculateScore(Criteria criteria) {
      score = 0;
      for (Criterion criterion: criteria) 
         if (criterion.matches(answerMatching(criterion))) 
            score += criterion.getWeight().getValue();
   }

   private boolean anyMatches(Criteria criteria) {
      boolean anyMatches = false;
      for (Criterion criterion: criteria) 
         anyMatches |= criterion.matches(answerMatching(criterion));
      return anyMatches;
   }

   private Answer answerMatching(Criterion criterion) {
      return answers.get(criterion.getAnswer().getQuestionText());
   }

   public int score() {
      return score;
   }

   public List<Answer> classicFind(Predicate<Answer> pred) {
      List<Answer> results = new ArrayList<Answer>();
      for (Answer answer: answers.values())
         if (pred.test(answer))
            results.add(answer);
      return results;
   }
   
   @Override
   public String toString() {
     return name;
   }

   public List<Answer> find(Predicate<Answer> pred) {
      return answers.values().stream()
            .filter(pred)
            .collect(Collectors.toList());
   }
}
