/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package util;

import org.junit.*;
import scratch.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class SparseArrayTest {
   private SparseArray<Object> array;

   @Before
   public void create() {
      array = new SparseArray<>();
   }

//   @Test
//   public void answersElementAtIndex() {
//      array.put(5, "five");
//      assertThat(array.get(5), equalTo("five"));
//   }
//
//   @Test
//   public void answersNullWhenEntryNotFound() {
//      assertThat(array.get(4000), nullValue());
//   }
//   
//   @Test
//   public void supportsMultipleEntries() {
//      array.put(6, "six");
//      array.put(7, "seven");
//      assertThat(array.get(6), equalTo("six"));
//      assertThat(array.get(7), equalTo("seven"));
//   }
//   
//   @Test
//   public void answersReplacedValue() {
//      array.put(6, "six");
//      array.put(6, "seis");
//      assertThat(array.get(6), equalTo("seis"));
//   }
//   
//   @Test
//   public void answersSize() {
//      array.put(4, "four");
//      assertThat(array.size(), equalTo(1));
//      array.put(5, "five");
//      assertThat(array.size(), equalTo(2));
//      array.put(5, "cinco");
//      assertThat(array.size(), equalTo(2));
//   }
//   
//   @Test
//   public void putOfNullValueDoesNotIncreaseSize() {
//      array.put(6, null);
//      assertThat(array.size(), equalTo(0));
//   }
   
   @ExpectToFail
   @Ignore
   @Test
   public void handlesInsertionInDescendingOrder() {
      array.put(7, "seven");
      array.checkInvariants();
      array.put(6, "six");
      array.checkInvariants();
      assertThat(array.get(6), equalTo("six"));
      assertThat(array.get(7), equalTo("seven"));
   }
   
//   @Test
//   public void handlesNumerousInsertions() {
//      array.put(20, "twenty");
//      array.put(80, "eighty");
//      array.put(50, "fifty");
//      array.put(40, "forty");
//      array.put(30, "thirty");
//      array.put(50, "cincuenta");
//      
//      assertThat(array.get(20), equalTo("twenty"));
//      assertThat(array.get(80), equalTo("eighty"));
//      assertThat(array.get(50), equalTo("cincuenta"));
//      assertThat(array.get(40), equalTo("forty"));
//      assertThat(array.get(30), equalTo("thirty"));
//   }

   private static final int[] TEN_THRU_SEVENTY = {10, 20, 30, 40, 50, 60, 70};

   @Test
   public void binarySearchFindsMidpoint() {
      assertThat(array.binarySearch(40, TEN_THRU_SEVENTY, 7), equalTo(3));
   }
   
   @Test
   public void binarySearchAnswersInsertAfterWhenNotFound() {
      assertThat(array.binarySearch(44, TEN_THRU_SEVENTY, 7), equalTo(3));
      assertThat(array.binarySearch(2, TEN_THRU_SEVENTY, 7), equalTo(-1));
      assertThat(array.binarySearch(77, TEN_THRU_SEVENTY, 7), equalTo(6));
   }
   
   @Test
   public void binarySearchLessThanMid() {
      assertThat(array.binarySearch(20, TEN_THRU_SEVENTY, 7), equalTo(1));
   }

   @Test
   public void binarySearchMoreThanMid() {
      assertThat(array.binarySearch(50, TEN_THRU_SEVENTY, 7), equalTo(4));
   }
   
   @Test
   public void binarySearchKeepsTrying() {
      assertThat(array.binarySearch(30, TEN_THRU_SEVENTY, 7), equalTo(2));
   }
}
