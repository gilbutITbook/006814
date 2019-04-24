/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package util;

public class Match {
   public final String searchString;
   public final String surroundingContext;
   public final String searchTitle;
   
   public Match(String searchTitle, String searchString, String surroundingContext) {
      this.searchTitle = searchTitle;
      this.searchString = searchString;
      this.surroundingContext = surroundingContext;
   }
}
