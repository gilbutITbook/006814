/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package iloveyouboss.controller;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.time.*;
import java.util.*;
import java.util.stream.*;
import iloveyouboss.domain.*;
import org.junit.*;

public class QuestionControllerTest {

   private QuestionController controller;
   @Before
   public void create() {
      controller = new QuestionController();
      controller.deleteAll();
   }

   @Test
   public void findsPersistedQuestionById() {
      int id = controller.addBooleanQuestion("question text");
      
      Question question = controller.find(id);
      
      assertThat(question.getText(), equalTo("question text"));
   }
   
   @Test
   public void questionAnswersDateAdded() {
      Instant now = new Date().toInstant();
      controller.setClock(Clock.fixed(now, ZoneId.of("America/Denver")));
      int id = controller.addBooleanQuestion("text");
      
      Question question = controller.find(id);
      
      assertThat(question.getCreateTimestamp(), equalTo(now));
   }
   
   @Test
   public void answersMultiplePersistedQuestions() {
      controller.addBooleanQuestion("q1");
      controller.addBooleanQuestion("q2");
      controller.addPercentileQuestion("q3", new String[] { "a1", "a2"});
      
      List<Question> questions = controller.getAll();
      
      assertThat(
         questions.stream().map(Question::getText).collect(Collectors.toList()), 
         equalTo(Arrays.asList("q1", "q2", "q3")));
   }
   
   @Test
   public void findsMatchingEntries() {
      controller.addBooleanQuestion("alpha 1");
      controller.addBooleanQuestion("alpha 2");
      controller.addBooleanQuestion("beta 1");

      List<Question> questions = controller.findWithMatchingText("alpha");
      
      assertThat(
         questions.stream().map(Question::getText).collect(Collectors.toList()),
         equalTo(Arrays.asList("alpha 1", "alpha 2")));
   }
}
