/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package util;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;
import java.util.logging.*;

public class Search {
   private String searchString;
   private Exception exception = null;
   private List<Match> matches = new ArrayList<>();
   private int surroundingCharacterCount = 100;
   private InputStream inputStream;
   final static Logger LOGGER = Logger.getLogger(Search.class.getName());
   private String searchTitle;

   public Search(String urlString, String searchString, String searchTitle) throws IOException {
      this(new URL(urlString).openConnection().getInputStream(), searchString, searchTitle);
   }

   public Search(InputStream inputStream, String searchString, String searchTitle) {
      this.inputStream = inputStream;
      this.searchString = searchString;
      this.searchTitle = searchTitle;
   }

   public List<Match> getMatches() {
      return matches;
   }

   public boolean errored() {
      return exception != null;
   }

   public Exception getError() {
      return exception;
   }

   public void execute() {
      try {
         search();
      } catch (IOException exc) {
         exception = exc;
      }
   }
   
   public void setSurroundingCharacterCount(int count) {
      surroundingCharacterCount = count;
   }

   private void addMatches(String line, Pattern pattern) {
      Matcher matcher = pattern.matcher(line);
      while (matcher.find()) {
         int start = matcher.start();
         int end = matcher.end();
         start -= surroundingCharacterCount;
         if (start <= 0) start = 0;
         end += surroundingCharacterCount;
         if (end >= line.length()) end = line.length();
         matches.add(new Match(searchTitle, searchString, line.substring(start, end)));
      }
   }

   private void search() throws IOException {
      Pattern pattern = Pattern.compile(searchString);
      matches.clear();

      LOGGER.info("searching matches for pattern:" + pattern);

      BufferedReader reader = null;
      try {
         reader = new BufferedReader(new InputStreamReader(inputStream));
         String line;
         while ((line = reader.readLine()) != null) {
            addMatches(line, pattern);
         }
      } finally {
         if (reader != null)
            reader.close();
      }
   }
}
