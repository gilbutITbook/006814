/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package iloveyouboss;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class AnswerCollectionTest {
   private AnswerCollection answers;

   @Before
   public void createProfile() {
      answers = new AnswerCollection();
   }

   int[] ids(Collection<Answer> answers) {
      return answers.stream()
            .mapToInt(a -> a.getQuestion().getId()).toArray();
   }

   @Test
   public void findsAnswersBasedOnPredicate() {
      answers.add(new Answer(new BooleanQuestion(1, "1"), Bool.FALSE));
      answers.add(new Answer(new PercentileQuestion(2, "2", new String[] {}), 0));
      answers.add(new Answer(new PercentileQuestion(3, "3", new String[] {}), 0));

      List<Answer> result =
            answers.find(a -> a.getQuestion().getClass() == PercentileQuestion.class);

      assertThat(ids(result), equalTo(new int[] { 2, 3 }));
   }
}
