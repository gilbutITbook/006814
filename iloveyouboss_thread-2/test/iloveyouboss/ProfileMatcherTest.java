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
import java.util.*;
import java.util.stream.*;
import org.junit.*;

public class ProfileMatcherTest {
   private BooleanQuestion question;
   private Criteria criteria;
   private ProfileMatcher matcher;
   private Profile matchingProfile;
   private Profile nonMatchingProfile;

   @Before
   public void create() {
      question = new BooleanQuestion(1, "");
      criteria = new Criteria();
      criteria.add(new Criterion(matchingAnswer(), Weight.MustMatch));
      matchingProfile = createMatchingProfile("matching");
      nonMatchingProfile = createNonMatchingProfile("nonMatching");
   }

   private Profile createMatchingProfile(String name) {
      Profile profile = new Profile(name);
      profile.add(matchingAnswer());
      return profile;
   }

   private Profile createNonMatchingProfile(String name) {
      Profile profile = new Profile(name);
      profile.add(nonMatchingAnswer());
      return profile;
   }
   
   @Before
   public void createMatcher() {
      matcher = new ProfileMatcher();
   }
   
   @Test
   public void collectsMatchSets() {
      matcher.add(matchingProfile);
      matcher.add(nonMatchingProfile);

      List<MatchSet> sets = matcher.collectMatchSets(criteria);
      
      assertThat(sets.stream()
                .map(set->set.getProfileId()).collect(Collectors.toSet()),
       equalTo(new HashSet<>
          (Arrays.asList(matchingProfile.getId(), nonMatchingProfile.getId()))));
   }

   private Answer matchingAnswer() {
      return new Answer(question, Bool.TRUE);
   }

   private Answer nonMatchingAnswer() {
      return new Answer(question, Bool.FALSE);
   }
}
