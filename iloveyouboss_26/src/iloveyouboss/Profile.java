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

   private Map<String, Answer> answers = new HashMap<>();

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
      MatchSet matchSet = new MatchSet(criteria, answers);
      score = matchSet.score;
      if (matchSet.doesNotMeetAnyMustMatchCriterion)
         return false;
      return matchSet.anyMatches();
   }

   static class MatchSet {
      final List<Criterion> matching = new ArrayList<>();
      final List<Criterion> notMatching = new ArrayList<>();
      int score = 0;
      boolean doesNotMeetAnyMustMatchCriterion = false;
      private final Map<String, Answer> answers;

      MatchSet(Criteria criteria, Map<String, Answer> answers) {
         this.answers = answers;
         for (Criterion criterion: criteria)
            if (criterion.matches(answerMatching(criterion)))
               addMatch(criterion);
            else
               addMismatch(criterion);
      }

      boolean anyMatches() {
         return !matching.isEmpty();
      }

      void addMatch(Criterion criterion) {
         matching.add(criterion);
         score += criterion.getWeight().getValue();
      }

      void addMismatch(Criterion criterion) {
         notMatching.add(criterion);
         if (criterion.getWeight() == Weight.MustMatch)
            doesNotMeetAnyMustMatchCriterion = true;
      }

      private Answer answerMatching(Criterion criterion) {
         return answers.get(criterion.getAnswer().getQuestionText());
      }
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
      return answers.values().stream().filter(pred).collect(Collectors.toList());
   }
}
