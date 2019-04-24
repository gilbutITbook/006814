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

public class Profile {
   private Map<String,Answer> answers = new HashMap<>();
   
   private Answer getMatchingProfileAnswer(Criterion criterion) {
      return answers.get(criterion.getAnswer().getQuestionText());
   }


   public boolean matches(Criteria criteria) {
      boolean matches = false;
      for (Criterion criterion: criteria) {
         if (matches(criterion))
            matches = true;
         else if (criterion.getWeight() == Weight.MustMatch)
            return false;
      }
      return matches;
   }

   public boolean matches(Criterion criterion) {
      return 
         criterion.getWeight() == Weight.DontCare ||
         criterion.getAnswer().match(getMatchingProfileAnswer(criterion));
   }

   public void add(Answer answer) {
      answers.put(answer.getQuestionText(), answer);
   }
}
