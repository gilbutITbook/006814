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
import static org.hamcrest.CoreMatchers.*;

public class ProfileTest {
   // ...
   private Profile profile;
   private BooleanQuestion questionIsThereRelocation;
   private Answer answerThereIsRelocation;
   private Answer answerThereIsNotRelocation;
   private BooleanQuestion questionReimbursesTuition;
   private Answer answerDoesNotReimburseTuition;
   private Answer answerReimbursesTuition;
   private Criteria criteria;
   
   @Before
   public void createCriteria() {
      criteria = new Criteria();
   }

   @Before
   public void createProfile() {
      profile = new Profile();
   }
   
   @Before
   public void createQuestionsAndAnswers() {
      questionIsThereRelocation = 
            new BooleanQuestion(1, "Relocation package?");
      answerThereIsRelocation = 
            new Answer(questionIsThereRelocation, Bool.TRUE);
      answerThereIsNotRelocation = 
            new Answer(questionIsThereRelocation, Bool.FALSE);

      questionReimbursesTuition = new BooleanQuestion(1, "Reimburses tuition?");
      answerDoesNotReimburseTuition = 
         new Answer(questionReimbursesTuition, Bool.FALSE);
      answerReimbursesTuition = 
         new Answer(questionReimbursesTuition, Bool.TRUE);
   }
  
   @Test
   public void matchesWhenProfileContainsMatchingAnswer() {
      profile.add(answerThereIsRelocation);
      Criterion criterion = 
            new Criterion(answerThereIsRelocation, Weight.Important);

      assertTrue(profile.matches(criterion));
   }
   
   @Test
   public void doesNotMatchWhenNoMatchingAnswer() {
      profile.add(answerThereIsNotRelocation);
      Criterion criterion = 
            new Criterion(answerThereIsRelocation, Weight.Important);
      
      assertFalse(profile.matches(criterion));
   }

   @Test
   public void matchesWhenContainsMultipleAnswers() {
      profile.add(answerThereIsRelocation);
      profile.add(answerDoesNotReimburseTuition);
      Criterion criterion = 
            new Criterion(answerThereIsRelocation, Weight.Important);
      
      assertTrue(profile.matches(criterion));
   }

   @Test
   public void doesNotMatchWhenNoneOfMultipleCriteriaMatch() {
      profile.add(answerDoesNotReimburseTuition);
      criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
      criteria.add(new Criterion(answerReimbursesTuition, Weight.Important));
      
      assertFalse(profile.matches(criteria));
   }

   @Test
   public void matchesWhenAnyOfMultipleCriteriaMatch() {
      profile.add(answerThereIsRelocation);
      criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
      criteria.add(new Criterion(answerReimbursesTuition, Weight.Important));
      
      assertTrue(profile.matches(criteria));
   }
   
   @Test
   public void doesNotMatchWhenAnyMustMeetCriteriaNotMet() {
      profile.add(answerThereIsRelocation);
      profile.add(answerDoesNotReimburseTuition);
      criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
      criteria.add(new Criterion(answerReimbursesTuition, Weight.MustMatch));
      
      assertFalse(profile.matches(criteria));
   }

   @Test
   public void matchesWhenCriterionIsDontCare() {
      profile.add(answerDoesNotReimburseTuition);
      Criterion criterion = 
         new Criterion(answerReimbursesTuition, Weight.DontCare);
      
      assertTrue(profile.matches(criterion));
   }
   
   @Test
   public void scoreIsZeroWhenThereAreNoMatches() {
      criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));

      ProfileMatch match = profile.match(criteria);

      assertThat(match.getScore(), equalTo(0));
   }
}
