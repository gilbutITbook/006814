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

   @Before
   public void createProfile() {
      profile = new Profile();
   }

   @Test
   public void matchesNothingWhenProfileEmpty() {
      Question question = new BooleanQuestion(1, "Relocation package?");
      Criterion criterion = 
         new Criterion(new Answer(question, Bool.TRUE), Weight.DontCare);
      
      boolean result = profile.matches(criterion);
      
      assertFalse(result);
   }
   // ...
  
   @Test
   public void matchesWhenProfileContainsMatchingAnswer() {
      Question question = new BooleanQuestion(1, "Relocation package?");
      Answer answer = new Answer(question, Bool.TRUE);
      profile.add(answer);
      Criterion criterion = new Criterion(answer, Weight.Important);

      boolean result = profile.matches(criterion);

      assertTrue(result);
   }
}
