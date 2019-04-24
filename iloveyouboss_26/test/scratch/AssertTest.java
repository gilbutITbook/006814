/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package scratch;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.io.*;
import java.util.*;
import org.junit.*;
import static scratch.PointMatcher.isNear;
// ...
import org.junit.rules.*;
// ...

public class AssertTest {

   class InsufficientFundsException extends RuntimeException {
      public InsufficientFundsException(String message) {
         super(message);
      }

      private static final long serialVersionUID = 1L;
   }

   class Account {
      int balance;
      String name;

      Account(String name) {
         this.name = name;
      }

      void deposit(int dollars) {
         balance += dollars;
      }

      void withdraw(int dollars) {
         if (balance < dollars) {
            throw new InsufficientFundsException("balance only " + balance);
         }
         balance -= dollars;
      }

      public String getName() {
         return name;
      }

      public int getBalance() {
         return balance;
      }

      public boolean hasPositiveBalance() {
         return balance > 0;
      }
   }

   class Customer {
      List<Account> accounts = new ArrayList<>();

      void add(Account account) {
         accounts.add(account);
      }

      Iterator<Account> getAccounts() {
         return accounts.iterator();
      }
   }

   private Account account;

   @Before
   public void createAccount() {
      account = new Account("an account name");
   }


   @Test
   public void hasPositiveBalance() {
      account.deposit(50);
      assertTrue(account.hasPositiveBalance());
   }


   @Test
   public void depositIncreasesBalance() {
      int initialBalance = account.getBalance();
      account.deposit(100);
      assertTrue(account.getBalance() > initialBalance);
      assertThat(account.getBalance(), equalTo(100));
   }

   
   @Test
   public void depositIncreasesBalance_hamcrestAssertTrue() {
      account.deposit(50);
      assertThat(account.getBalance() > 0, is(true));
   }
   
   @Ignore
   @Test
   public void testWithWorthlessAssertionComment() {
      account.deposit(50);
      assertThat("account balance is 100", account.getBalance(), equalTo(50));
   }

   @Test
   @ExpectToFail
   @Ignore
   public void assertFailure() {
      assertTrue(account.getName().startsWith("xyz"));
   }

   @Test
   @ExpectToFail
   @Ignore
   public void matchesFailure() {
      assertThat(account.getName(), startsWith("xyz"));
   }

   @Test
   public void variousMatcherTests() {
      Account account = new Account("my big fat acct");
      assertThat(account.getName(), is(equalTo("my big fat acct")));

      assertThat(account.getName(), allOf(startsWith("my"), endsWith("acct")));

      assertThat(account.getName(), anyOf(startsWith("my"), endsWith("loot")));

      assertThat(account.getName(), not(equalTo("plunderings")));

      assertThat(account.getName(), is(not(nullValue())));
      assertThat(account.getName(), is(notNullValue()));

      assertThat(account.getName(), isA(String.class));

      assertThat(account.getName(), is(notNullValue())); // not helpful
      assertThat(account.getName(), equalTo("my big fat acct"));
   }

   @Test
   public void sameInstance() {
      Account a = new Account("a");
      Account aPrime = new Account("a");
      // TODO why needs to be fully qualified??
      assertThat(a, not(org.hamcrest.CoreMatchers.sameInstance(aPrime)));
   }

   @Test
   public void moreMatcherTests() {
      Account account = new Account(null);
      assertThat(account.getName(), is(nullValue()));
   }

   @Test
   @SuppressWarnings("unchecked")
   public void items() {
      List<String> names = new ArrayList<>();
      names.add("Moe");
      names.add("Larry");
      names.add("Curly");

      assertThat(names, hasItem("Curly"));

      assertThat(names, hasItems("Curly", "Moe"));

      assertThat(names, hasItem(endsWith("y")));

      assertThat(names, hasItems(endsWith("y"), startsWith("C"))); //warning!

      assertThat(names, not(everyItem(endsWith("y"))));
   }

   @Test
   @ExpectToFail @Ignore
   public void location() {
      Point point = new Point(4, 5);
      
      // WTF why do JUnit matches not include closeTo
//      assertThat(point.x, closeTo(3.6, 0.2));
//      assertThat(point.y, closeTo(5.1, 0.2));
      
      assertThat(point, isNear(3.6, 5.1));
   }
   
   @Test
   @ExpectToFail @Ignore
   public void classicAssertions() {
      Account account = new Account("acct namex");
      assertEquals("acct name", account.getName());
   }
   
   @Test(expected=InsufficientFundsException.class)
   public void throwsWhenWithdrawingTooMuch() {
      account.withdraw(100);
   }
   
   @Test
   public void throwsWhenWithdrawingTooMuchTry() {
      try {
         account.withdraw(100);
         fail();
      }
      catch (InsufficientFundsException expected) {
         assertThat(expected.getMessage(), equalTo("balance only 0"));
      }
   }
   
   @Test
   public void readsFromTestFile() throws IOException {
      String filename = "test.txt";
      BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
      writer.write("test data");
      writer.close();
      // ...
   }
   
   @After
   public void deleteForReadsFromTestFile() {
      new File("test.txt").delete();
   }
   
   @Test
   @Ignore("don't forget me!")
   public void somethingWeCannotHandleRightNow() {
      // ...
   }

   @Rule
   public ExpectedException thrown = ExpectedException.none();
   
   @Test
   public void exceptionRule() {
      thrown.expect(InsufficientFundsException.class);
      thrown.expectMessage("balance only 0");
      
      account.withdraw(100);
   }
   
   @Test
   public void doubles() {
      assertEquals(9.7, 10.0 - 0.3, 0.005);
   }
}
