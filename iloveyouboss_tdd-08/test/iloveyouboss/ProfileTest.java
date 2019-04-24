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
   private BooleanQuestion questionIsThereRelocation;
   private Answer answerThereIsRelocation;

   @Before
   public void createProfile() {
      profile = new Profile();
   }
   
   @Before
   public void createQuestionAndAnswer() {
      questionIsThereRelocation = 
            new BooleanQuestion(1, "Relocation package?");
      answerThereIsRelocation = 
            new Answer(questionIsThereRelocation, Bool.TRUE);
   }

   @Test
   public void matchesNothingWhenProfileEmpty() {
      Criterion criterion = 
            new Criterion(answerThereIsRelocation, Weight.DontCare);
      
      boolean result = profile.matches(criterion);
      
      assertFalse(result);
   }
  
   @Test
   public void matchesWhenProfileContainsMatchingAnswer() {
      profile.add(answerThereIsRelocation);
      Criterion criterion = 
            new Criterion(answerThereIsRelocation, Weight.Important);

      boolean result = profile.matches(criterion);

      assertTrue(result);
   }
}
