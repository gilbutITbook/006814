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
import static org.hamcrest.CoreMatchers.*; 
import org.junit.*;

public class ScoreCollectionTest {
   private ScoreCollection collection;

   @Before
   public void create() {
      collection = new ScoreCollection();
   }

   @Test
   public void answersArithmeticMeanOfTwoNumbers() {
      collection.add(() -> 5);
      collection.add(() -> 7);
      
      int actualResult = collection.arithmeticMean();
      
      assertThat(actualResult, equalTo(6));
   }
   
   @Test(expected=IllegalArgumentException.class)
   public void throwsExceptionWhenAddingNull() {
      collection.add(null);
   }
   
   @Test
   public void answersZeroWhenNoElementsAdded() {
      assertThat(collection.arithmeticMean(), equalTo(0));
   }

   @Test
   public void dealsWithIntegerOverflow() {
      collection.add(() -> Integer.MAX_VALUE); 
      collection.add(() -> 1); 
      
      assertThat(collection.arithmeticMean(), equalTo(1073741824));
   }
}

