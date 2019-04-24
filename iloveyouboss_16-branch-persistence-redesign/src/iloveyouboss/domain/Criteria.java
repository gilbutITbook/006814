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

public class Criteria implements Iterable<Criterion> {

   private List<Criterion> criteria = new ArrayList<>();

   public void add(Criterion criterion) {
      criteria.add(criterion);
   }

   @Override
   public Iterator<Criterion> iterator() {
      return criteria.iterator();
   }
   
   public int arithmeticMean() {
      return 0;
   }

   public double geometricMean(int[] numbers) {
      int totalProduct = Arrays.stream(numbers).reduce(1, (product, number) -> product * number);
      return Math.pow(totalProduct, 1.0 / numbers.length);
   }
}
