/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package iloveyouboss;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;
import static org.hamcrest.CoreMatchers.*;

public class MatchSetTest {
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

   private Map<String,Answer> answers;

   @Before
   public void createAnswers() {
      answers = new HashMap<>();
   }

   @Before
   public void createCriteria() {
      criteria = new Criteria();
   }

   @Before
   public void createQuestionsAndAnswers() {
      questionIsThereRelocation =
            new BooleanQuestion(1, "Relocation package?");
      answerThereIsRelocation =
            new Answer(questionIsThereRelocation, Bool.TRUE);
      answerThereIsNoRelocation =
            new Answer(questionIsThereRelocation, Bool.FALSE);

      questionReimbursesTuition =
            new BooleanQuestion(1, "Reimburses tuition?");
      answerReimbursesTuition =
            new Answer(questionReimbursesTuition, Bool.TRUE);
      answerDoesNotReimburseTuition =
            new Answer(questionReimbursesTuition, Bool.FALSE);

      questionOnsiteDaycare =
            new BooleanQuestion(1, "Onsite daycare?");
      answerHasOnsiteDaycare =
            new Answer(questionOnsiteDaycare, Bool.TRUE);
      answerNoOnsiteDaycare =
            new Answer(questionOnsiteDaycare, Bool.FALSE);
   }

   private void add(Answer answer) {
      answers.put(answer.getQuestionText(), answer);
   }

   private MatchSet createMatchSet() {
      return new MatchSet("", answers, criteria);
   }

   @Test
   public void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
      add(answerDoesNotReimburseTuition);
      criteria.add(
            new Criterion(answerReimbursesTuition, Weight.MustMatch));

      assertFalse(createMatchSet().matches());
   }

   @Test
   public void matchAnswersTrueForAnyDontCareCriteria() {
      add(answerDoesNotReimburseTuition);
      criteria.add(
            new Criterion(answerReimbursesTuition, Weight.DontCare));

      assertTrue(createMatchSet().matches());
   }

   @Test
   public void matchAnswersTrueWhenAnyOfMultipleCriteriaMatch() {
      add(answerThereIsRelocation);
      add(answerDoesNotReimburseTuition);
      criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
      criteria.add(new Criterion(answerReimbursesTuition, Weight.Important));

      assertTrue(createMatchSet().matches());
   }

   @Test
   public void matchAnswersFalseWhenNoneOfMultipleCriteriaMatch() {
      add(answerThereIsNoRelocation);
      add(answerDoesNotReimburseTuition);
      criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
      criteria.add(new Criterion(answerReimbursesTuition, Weight.Important));

      assertFalse(createMatchSet().matches());
   }

   @Test
   public void scoreIsZeroWhenThereAreNoMatches() {
      add(answerThereIsNoRelocation);
      criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));

      assertThat(createMatchSet().getScore(), equalTo(0));
   }

   @Test
   public void scoreIsCriterionValueForSingleMatch() {
      add(answerThereIsRelocation);
      criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));

      assertThat(createMatchSet().getScore(), equalTo(Weight.Important.getValue()));
   }

   @Test
   public void scoreAccumulatesCriterionValuesForMatches() {
      add(answerThereIsRelocation);
      add(answerReimbursesTuition);
      add(answerNoOnsiteDaycare);
      criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
      criteria.add(new Criterion(answerReimbursesTuition, Weight.WouldPrefer));
      criteria.add(new Criterion(answerHasOnsiteDaycare, Weight.VeryImportant));

      int expectedScore = Weight.Important.getValue() + Weight.WouldPrefer.getValue();
      assertThat(createMatchSet().getScore(), equalTo(expectedScore));
   }

   // TODO: missing functionality--what if there is no matching profile answer for a criterion?

   @Test
   public void sortsByScore() {
      add(new Answer(questionReimbursesTuition, Bool.FALSE));
      criteria.add(
         new Criterion(new Answer(questionReimbursesTuition, Bool.TRUE),
               Weight.Important));

      MatchSet smallerSet = new MatchSet("1", null, null) {
         @Override public int getScore() { return 1; }
      };
      MatchSet largerSet = new MatchSet("2", null, null) {
         @Override public int getScore() { return 2; }
      };

      assertTrue(smallerSet.compareTo(largerSet) < 0);
   }
}

