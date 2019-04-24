/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package iloveyouboss;

import java.io.*;
import org.json.simple.parser.*;
import org.junit.*;
import org.mockito.*;
import util.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AddressRetrieverTest {
   @Mock private Http http;
   @InjectMocks private AddressRetriever retriever;
   
   @Before
   public void createRetriever() {
      retriever = new AddressRetriever();
      MockitoAnnotations.initMocks(this);
   }

   @Test
   public void answersAppropriateAddressForValidCoordinates() 
         throws IOException, ParseException {
      when(http.get(contains("lat=38.000000&lon=-104.000000")))
         .thenReturn("{\"address\":{"
                        + "\"house_number\":\"324\","
         // ...
                        + "\"road\":\"North Tejon Street\","
                        + "\"city\":\"Colorado Springs\","
                        + "\"state\":\"Colorado\","
                        + "\"postcode\":\"80903\","
                        + "\"country_code\":\"us\"}"
                        + "}");

      Address address = retriever.retrieve(38.0,-104.0);
      
      assertThat(address.houseNumber, equalTo("324"));
      assertThat(address.road, equalTo("North Tejon Street"));
      assertThat(address.city, equalTo("Colorado Springs"));
      assertThat(address.state, equalTo("Colorado"));
      assertThat(address.zip, equalTo("80903"));
   }
}
