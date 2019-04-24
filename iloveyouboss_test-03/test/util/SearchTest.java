/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package util;

// introduce custom matcher abstraction
import java.io.*;
import java.net.*;
import java.util.*;
import org.junit.*;
import java.util.logging.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static util.ContainsMatches.*;

public class SearchTest {
   @Test
   public void testSearch() throws IOException {
      String pageContent = "There are certain queer times and occasions "
            // ...
            + "in this strange mixed affair we call life when a man "
            + "takes this whole universe for a vast practical joke, "
            + "though the wit thereof he but dimly discerns, and more "
            + "than suspects that the joke is at nobody's expense but "
            + "his own.";
      byte[] bytes = pageContent.getBytes();
      ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
      // search
      Search search = new Search(stream, "practical joke", "1");
      Search.LOGGER.setLevel(Level.OFF);
      search.setSurroundingCharacterCount(10);
      search.execute();
      assertFalse(search.errored());
      assertThat(search.getMatches(), containsMatches(new Match[] { 
         new Match("1", "practical joke", 
                   "or a vast practical joke, though t") }));
      stream.close();
      // ...

      // negative
      URLConnection connection = 
            new URL("http://bit.ly/15sYPA7").openConnection();
      InputStream inputStream = connection.getInputStream();
      search = new Search(inputStream, "smelt", "http://bit.ly/15sYPA7");
      search.execute();
      assertTrue(search.getMatches().isEmpty());
      stream.close();
   }
}
