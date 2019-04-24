/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package iloveyouboss.domain;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ProfileTest {
   private Profile profile;
   private Criteria criteria;

   private Question questionReimbursesTuition;
   private Answer answerReimbursesTuition;
   private Answer answerDoesNotReimburseTuition;

   private Question questionIsThereRelocation;
   private Answer answerThereIsRelocation;
   private Answer answerThereIsNoRelocation;

   private Question questionOnsiteDaycare;
   private Answer answerNoOnsiteDaycare;
   private Answer answerHasOnsiteDaycare;

   @Before
   public void createProfile() {
      profile = new Profile("Bull Hockey, Inc.");
   }

   @Before
   public void createCriteria() {
      criteria = new Criteria();
   }

   @Before
   public void createQuestionsAndAnswers() {
      questionIsThereRelocation =
            new BooleanQuestion("Relocation package?");
      answerThereIsRelocation =
            new Answer(questionIsThereRelocation, Bool.TRUE);
      answerThereIsNoRelocation =
            new Answer(questionIsThereRelocation, Bool.FALSE);

      questionReimbursesTuition =
            new BooleanQuestion("Reimburses tuition?");
      answerReimbursesTuition =
            new Answer(questionReimbursesTuition, Bool.TRUE);
      answerDoesNotReimburseTuition =
            new Answer(questionReimbursesTuition, Bool.FALSE);

      questionOnsiteDaycare =
            new BooleanQuestion("Onsite daycare?");
      answerHasOnsiteDaycare =
            new Answer(questionOnsiteDaycare, Bool.TRUE);
      answerNoOnsiteDaycare =
            new Answer(questionOnsiteDaycare, Bool.FALSE);
   }

   @Test
   public void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
      profile.add(answerDoesNotReimburseTuition);
      criteria.add(
            new Criterion(answerReimbursesTuition, Weight.MustMatch));

      boolean matches = profile.matches(criteria);

      assertFalse(matches);
   }

   @Test
   public void matchAnswersTrueForAnyDontCareCriteria() {
      profile.add(answerDoesNotReimburseTuition);
      criteria.add(
            new Criterion(answerReimbursesTuition, Weight.DontCare));

      boolean matches = profile.matches(criteria);

      assertTrue(matches);
   }

   @Test
   public void matchAnswersTrueWhenAnyOfMultipleCriteriaMatch() {
      profile.add(answerThereIsRelocation);
      profile.add(answerDoesNotReimburseTuition);
      criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
      criteria.add(new Criterion(answerReimbursesTuition, Weight.Important));

      boolean matches = profile.matches(criteria);

      assertTrue(matches);
   }

   @Test
   public void matchAnswersFalseWhenNoneOfMultipleCriteriaMatch() {
      profile.add(answerThereIsNoRelocation);
      profile.add(answerDoesNotReimburseTuition);
      criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
      criteria.add(new Criterion(answerReimbursesTuition, Weight.Important));

      boolean matches = profile.matches(criteria);

      assertFalse(matches);
   }

   @Test
   public void scoreIsZeroWhenThereAreNoMatches() {
      profile.add(answerThereIsNoRelocation);
      criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));

      profile.matches(criteria);

      assertThat(profile.score(), equalTo(0));
   }

   @Test
   public void scoreIsCriterionValueForSingleMatch() {
      profile.add(answerThereIsRelocation);
      criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));

      profile.matches(criteria);

      assertThat(profile.score(), equalTo(Weight.Important.getValue()));
   }

   @Test
   public void scoreAccumulatesCriterionValuesForMatches() {
      profile.add(answerThereIsRelocation);
      profile.add(answerReimbursesTuition);
      profile.add(answerNoOnsiteDaycare);
      criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
      criteria.add(new Criterion(answerReimbursesTuition, Weight.WouldPrefer));
      criteria.add(new Criterion(answerHasOnsiteDaycare, Weight.VeryImportant));

      profile.matches(criteria);

      int expectedScore = Weight.Important.getValue() + Weight.WouldPrefer.getValue();
      assertThat(profile.score(), equalTo(expectedScore));
   }

   // TODO: missing functionality--what if there is no matching profile answer for a criterion?
}

