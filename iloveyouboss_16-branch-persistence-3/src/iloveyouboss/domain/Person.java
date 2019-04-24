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
import java.util.stream.*;

public class Person {
   private List<Question> characteristics = new ArrayList<>();

   public void add(Question characteristic) {
      characteristics.add(characteristic);
   }

   public List<Question> getCharacteristics() {
      return characteristics;
   }

   public List<Question> withCharacteristic(String questionPattern) {
      return characteristics.stream().filter(c -> c.getText().endsWith(questionPattern)).collect(Collectors.toList());
   }

}

/*
// your answer
// their answer
// how important is it to you

me very organized
you very organized
very important

me no
you no


irrelevant 0
little 1
10
50
mandatory 250

how much did other person satisfy?
      
      multiply scores
      take nth root
      
      .98 * .94 take sqrt (2 questions)
      
      (geometric mean)

*/
