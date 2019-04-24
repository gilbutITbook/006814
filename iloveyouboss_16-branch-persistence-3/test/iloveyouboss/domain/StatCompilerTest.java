/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package iloveyouboss.domain;

import static org.junit.Assert.*;
import iloveyouboss.controller.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import org.junit.*;
import org.mockito.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

public class StatCompilerTest {
   @Test
   public void responsesByQuestionAnswersCountsByQuestionText() {
      StatCompiler stats = new StatCompiler();
      List<BooleanAnswer> answers = new ArrayList<>();
      answers.add(new BooleanAnswer(1, true));
      answers.add(new BooleanAnswer(1, true));
      answers.add(new BooleanAnswer(1, true));
      answers.add(new BooleanAnswer(1, false));
      answers.add(new BooleanAnswer(2, true));
      answers.add(new BooleanAnswer(2, true));
      Map<Integer, String> questions = new HashMap<>();
      questions.put(1, "Tuition reimbursement?");
      questions.put(2, "Relocation package?");

      Map<String, Map<Boolean, AtomicInteger>> responses = stats.responsesByQuestion(answers, questions);

      assertThat(responses.get("Tuition reimbursement?").get(Boolean.TRUE).get(), equalTo(3));
      assertThat(responses.get("Tuition reimbursement?").get(Boolean.FALSE).get(), equalTo(1));
      assertThat(responses.get("Relocation package?").get(Boolean.TRUE).get(), equalTo(2));
      assertThat(responses.get("Relocation package?").get(Boolean.FALSE).get(), equalTo(0));
   }

   @Mock private QuestionController controller;
   @InjectMocks private StatCompiler stats;

   @Before
   public void initialize() {
      stats = new StatCompiler();
      MockitoAnnotations.initMocks(this);
   }

   @Test
   public void questionTextDoesStuff() {
      when(controller.find(1)).thenReturn(new BooleanQuestion("text1"));
      when(controller.find(2)).thenReturn(new BooleanQuestion("text2"));
      List<BooleanAnswer> answers = new ArrayList<>();
      answers.add(new BooleanAnswer(1, true));
      answers.add(new BooleanAnswer(2, true));

      Map<Integer, String> questionText = stats.questionText(answers);

      Map<Integer, String> expected = new HashMap<>();
      expected.put(1, "text1");
      expected.put(2, "text2");
      assertThat(questionText, equalTo(expected));
   }
}
