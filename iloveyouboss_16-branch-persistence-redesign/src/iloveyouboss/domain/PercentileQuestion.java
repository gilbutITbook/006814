/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package iloveyouboss.domain;

import java.util.*;
import javax.persistence.*;

@Entity
@DiscriminatorValue(value="percentile")
public class PercentileQuestion extends Question {
   private static final long serialVersionUID = 1L;

   @ElementCollection
   @CollectionTable(name="AnswerChoice",
                    joinColumns=@JoinColumn(name="question_id"))
   private List<String> answerChoices;

   public PercentileQuestion() {}
   public PercentileQuestion(String text, String[] answerChoices) {
      super(text);
      this.answerChoices = Arrays.asList(answerChoices);
   }

   public List<String> getAnswerChoices() {
      return answerChoices;
   }

   public void setAnswerChoices(List<String> answerChoices) {
      this.answerChoices = answerChoices;
   }

   @Override
   public boolean match(int expected, int actual) {
      return expected <= actual;
   }
}
