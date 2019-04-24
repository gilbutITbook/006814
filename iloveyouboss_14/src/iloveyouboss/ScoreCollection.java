/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package iloveyouboss;

import java.util.*;

public class ScoreCollection {
   private List<Scoreable> scores = new ArrayList<>();
   
   public void add(Scoreable scoreable) {
      if (scoreable == null) throw new IllegalArgumentException();
      scores.add(scoreable);
   }
   
   public int arithmeticMean() {
      if (scores.size() == 0) return 0;
      // ...
      
      long total = scores.stream().mapToLong(Scoreable::getScore).sum();
      return (int)(total / scores.size());
   }
}
