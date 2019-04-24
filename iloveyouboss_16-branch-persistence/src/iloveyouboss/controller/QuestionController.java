/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package iloveyouboss.controller;

import iloveyouboss.domain.*;
import java.time.*;
import java.util.*;
import java.util.function.*;
import javax.persistence.*;

public class QuestionController {
   private Clock clock = Clock.systemUTC();
   // ...

   private static EntityManagerFactory getEntityManagerFactory() {
      return Persistence.createEntityManagerFactory("postgres-ds");
   }
   
   public Question find(Integer id) {
      return em().find(Question.class, id);
   }
   
   public List<Question> getAll() {
      return em().createQuery("select q from Question q", Question.class).getResultList();
   }
   
   public List<Question> findWithMatchingText(String text) {
      return em()
            .createQuery(
                "select q from Question q where q.text like '%" + text + "%'", Question.class)
            .getResultList();
   }
   
   public int addPercentileQuestion(String text, String[] answerChoices) {
      return persist(new PercentileQuestion(text, answerChoices));
   }

   public int addBooleanQuestion(String text) {
      return persist(new BooleanQuestion(text));
   }

   void setClock(Clock clock) {
      this.clock = clock;
   }
   // ...

   void deleteAll() {
      executeInTransaction(
            (em) -> em.createNativeQuery("delete from Question").executeUpdate());
   }
   
   private void executeInTransaction(Consumer<EntityManager> func) {
      EntityManager em = em();

      EntityTransaction transaction = em.getTransaction();
      try {
         transaction.begin();
         func.accept(em);
         transaction.commit();
      } catch (Throwable t) {
         t.printStackTrace();
         transaction.rollback();
      }
      finally {
        em.close();
      }
   }
   
   private int persist(Persistable object) {
      object.setCreateTimestamp(clock.instant());
      executeInTransaction((em) -> em.persist(object));
      return object.getId();
   }
   
   private EntityManager em() {
      return getEntityManagerFactory().createEntityManager();
   }
}
