/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package iloveyouboss;

public class Profile {
   private AnswerCollection answers = new AnswerCollection();
   private String name;

   public Profile(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public void add(Answer answer) {
      answers.add(answer);
   }

   public MatchSet getMatchSet(Criteria criteria) {
      return new MatchSet(answers, criteria);
   }
   // ...

   @Override
   public String toString() {
     return name;
   }
}
