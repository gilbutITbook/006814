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

public class ProfilePool {
   private List<Profile> profiles = new ArrayList<Profile>();

   public void add(Profile profile) {
      profiles.add(profile);
   }

   public void score(Criteria criteria) {
      for (Profile profile: profiles)
         profile.matches(criteria);
   }

   public List<Profile> ranked() {
      Collections.sort(profiles, 
            (p1, p2) -> -1 * ((Integer)p1.score()).compareTo(p2.score()));
      return profiles;
   }
}
