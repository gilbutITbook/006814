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
      MatchSet matching = matching(criteria);
      calculateScore(matching.matching);
      if (doesNotMeetAnyMustMatchCriterion(matching.notMatching))
         return false;
      return anyMatches(matching.matching);
   }
   
   class MatchSet {
      List<Criterion> matching = new ArrayList<>();
      List<Criterion> notMatching = new ArrayList<>();
   }
      
   private MatchSet matching(Criteria criteria) {
      MatchSet results = new MatchSet();
      for (Criterion criterion: criteria) 
         if (criterion.matches(answerMatching(criterion)))
            results.matching.add(criterion);
         else
            results.notMatching.add(criterion);
      return results;
   }

   private boolean doesNotMeetAnyMustMatchCriterion(List<Criterion> notMatching) {
      for (Criterion criterion: notMatching) 
         if (criterion.getWeight() == Weight.MustMatch) 
            return true;
      return false;
   }

   private void calculateScore(List<Criterion> matching) {
      score = 0;
      for (Criterion criterion: matching) 
         score += criterion.getWeight().getValue();
   }

   private boolean anyMatches(List<Criterion> criteria) {
      return !criteria.isEmpty();
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
