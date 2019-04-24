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
import java.util.function.*;
import java.util.stream.*;
import org.junit.*;
// ...
import static org.mockito.Mockito.*;
public class ProfileMatcherTest {
// ...
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
      
      assertThat(sets.stream().map(set -> set.getProfileId()).collect(Collectors.toSet()),
         equalTo(new HashSet<>(Arrays.asList(matchingProfile.getId(), nonMatchingProfile.getId()))));
   }
   
   private MatchListener listener;

   @Before
   public void createMatchListener() {
      listener = mock(MatchListener.class);
   }

   @Test
   public void processNotifiesListenerOnMatch() {
      matcher.add(matchingProfile);
      MatchSet set = matchingProfile.getMatchSet(criteria);

      matcher.process(listener, set);
      
      verify(listener).foundMatch(matchingProfile, set);
   }

   @Test
   public void processDoesNotNotifyListenerWhenNoMatch() {
      matcher.add(nonMatchingProfile);
      MatchSet set = nonMatchingProfile.getMatchSet(criteria);

      matcher.process(listener, set);
      
      verify(listener, never()).foundMatch(nonMatchingProfile, set);
   }

   @Test
   public void gathersMatchingProfiles() {
      Set<String> processedSets = 
            Collections.synchronizedSet(new HashSet<>()); // (1)
      BiConsumer<MatchListener, MatchSet> processFunction = 
            (listener, set) -> { // (2)
         processedSets.add(set.getProfileId()); // (3)
      };
      List<MatchSet> matchSets = createMatchSets(100); // (4)

      matcher.findMatchingProfiles( // (5)
            criteria, listener, matchSets, processFunction); 
      
      while (!matcher.getExecutor().isTerminated()) // (6)
         ;
      assertThat(processedSets, equalTo(matchSets.stream()
         .map(MatchSet::getProfileId).collect(Collectors.toSet()))); // (7)
   }

   private List<MatchSet> createMatchSets(int count) {
      List<MatchSet> sets = new ArrayList<>();
      for (int i = 0; i < count; i++)
         sets.add(new MatchSet(String.valueOf(i), null, null));
      return sets;
   }

   private Answer matchingAnswer() {
      return new Answer(question, Bool.TRUE);
   }

   private Answer nonMatchingAnswer() {
      return new Answer(question, Bool.FALSE);
   }
}
