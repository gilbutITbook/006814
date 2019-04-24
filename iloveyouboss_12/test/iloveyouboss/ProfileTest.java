/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package iloveyouboss;

import org.junit.*;
import static org.junit.Assert.*;

public class ProfileTest {
   private Profile profile;
   private BooleanQuestion question;
   private Criteria criteria;
   
   @Before
   public void create() {
      profile = new Profile("Bull Hockey, Inc.");
      question = new BooleanQuestion(1, "Got bonuses?");
      criteria = new Criteria();
   }
   
   @Test
   public void matches() {
      Profile profile = new Profile("Bull Hockey, Inc.");
      Question question = new BooleanQuestion(1, "Got milk?");

      // answers false when must-match criteria not met
      profile.add(new Answer(question, Bool.FALSE));      
      Criteria criteria = new Criteria();
      criteria.add(
            new Criterion(new Answer(question, Bool.TRUE), Weight.MustMatch));

      assertFalse(profile.matches(criteria));
      
      // answers true for any don't care criteria 
      profile.add(new Answer(question, Bool.FALSE));      
      criteria = new Criteria();
      criteria.add(
            new Criterion(new Answer(question, Bool.TRUE), Weight.DontCare));

      assertTrue(profile.matches(criteria));
   }
   
   @Test
   public void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
      profile.add(new Answer(question, Bool.FALSE));      
      criteria.add(
            new Criterion(new Answer(question, Bool.TRUE), Weight.MustMatch));

      boolean matches = profile.matches(criteria);
      
      assertFalse(matches);
   }
   
   @Test
   public void matchAnswersTrueForAnyDontCareCriteria() {
      profile.add(new Answer(question, Bool.FALSE));      
      criteria.add(
            new Criterion(new Answer(question, Bool.TRUE), Weight.DontCare));

      boolean matches = profile.matches(criteria);
      
      assertTrue(matches);
   }
}
