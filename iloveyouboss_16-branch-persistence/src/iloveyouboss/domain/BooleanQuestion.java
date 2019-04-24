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
@DiscriminatorValue(value="boolean")
public class BooleanQuestion extends Question {
   private static final long serialVersionUID = 1L;

   public BooleanQuestion() {}
   public BooleanQuestion(String text) {
      super(text);
   }

   @Override
   public List<String> getAnswerChoices() {
      return Arrays.asList(new String[] { "No", "Yes" });
   }

   @Override
   public boolean match(int expected, int actual) {
      return expected == actual;
   }
}
