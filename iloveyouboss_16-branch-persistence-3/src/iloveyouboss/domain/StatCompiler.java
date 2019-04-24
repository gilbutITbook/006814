/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package iloveyouboss.domain;

import iloveyouboss.controller.*;
import java.util.*;
import java.util.concurrent.atomic.*;

public class StatCompiler {
   static Question q1 = new BooleanQuestion("Tuition reimbursement?");
   static Question q2 = new BooleanQuestion("Relocation package?");

   private QuestionController controller = new QuestionController();
   
   public Map<Integer,String> questionText(List<BooleanAnswer> answers) {
      Map<Integer,String> questions = new HashMap<>();
      answers.stream().forEach(answer -> {
         if (!questions.containsKey(answer.getQuestionId()))
            questions.put(answer.getQuestionId(), 
               controller.find(answer.getQuestionId()).getText()); });
      return questions;
   }

   public Map<String, Map<Boolean, AtomicInteger>> responsesByQuestion(
         List<BooleanAnswer> answers, Map<Integer,String> questions) {
      Map<Integer, Map<Boolean, AtomicInteger>> responses = new HashMap<>();
      answers.stream().forEach(answer -> incrementHistogram(responses, answer));
      return convertHistogramIdsToText(responses, questions);
   }

   private Map<String, Map<Boolean, AtomicInteger>> convertHistogramIdsToText(
         Map<Integer, Map<Boolean, AtomicInteger>> responses, 
         Map<Integer,String> questions) {
      Map<String, Map<Boolean, AtomicInteger>> textResponses = new HashMap<>();
      responses.keySet().stream().forEach(id -> 
         textResponses.put(questions.get(id), responses.get(id)));
      return textResponses;
   }

   private void incrementHistogram(
         Map<Integer, Map<Boolean, AtomicInteger>> responses, BooleanAnswer answer) {
      Map<Boolean, AtomicInteger> histogram = 
            getHistogram(responses, answer.getQuestionId());
      histogram.get(Boolean.valueOf(answer.getValue())).getAndIncrement();
   }

   private Map<Boolean, AtomicInteger> getHistogram(
         Map<Integer, Map<Boolean, AtomicInteger>> responses, int id) {
      Map<Boolean, AtomicInteger> histogram = null;
      if (responses.containsKey(id)) 
         histogram = responses.get(id);
      else {
         histogram = createNewHistogram();
         responses.put(id, histogram);
      }
      return histogram;
   }

   private Map<Boolean, AtomicInteger> createNewHistogram() {
      Map<Boolean, AtomicInteger> histogram;
      histogram = new HashMap<>();
      histogram.put(Boolean.FALSE, new AtomicInteger(0));
      histogram.put(Boolean.TRUE, new AtomicInteger(0));
      return histogram;
   }
}
