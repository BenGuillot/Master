package fr.uvsq.poo.compte;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class AccountTest {
  private BigDecimal amount100;
  private BigDecimal amount200;
  private BigDecimal invalidAmount;
  private Account account100;
  private Account account200;

  @Before
  public void setup() {
    amount100 = new BigDecimal("100");
    amount200 = new BigDecimal("200");
    invalidAmount = new BigDecimal("-100");
    account100 = new Account(amount100);
    account200 = new Account(amount200);
  }

  @Test
  public void anAccountCreatedWithAnAmountShouldHaveABalanceEqualsToThisAmount() {
    assertThat(account100.getBalance(), is(equalTo(amount100)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void aCreationWithANegativeAmountShouldFail() {
    new Account(invalidAmount);
  }

  @Test
  public void anAccountCreditedWithAnAmountShouldHaveABalanceIncreasedByThisAmount() {
    account100.credit(amount100);
    assertThat(account100.getBalance(), is(equalTo(amount200)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void aCreditWithANegativeAmountShouldFail() {
    account100.credit(invalidAmount);
  }

  @Test
  public void anAccountDebitedWithAnAmountShouldHaveABalanceDecreasedByThisAmount() {
    account200.debit(amount100);
    assertThat(account200.getBalance(), is(equalTo(amount100)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void aDebitWithANegativeAmountShouldFail() {
    account100.debit(invalidAmount);
  }

  @Test(expected = IllegalArgumentException.class)
  public void aDebitWithAnAmountHigherThanTheBalanceShouldFail() {
    account100.debit(amount200);
  }

  @Test
  public void aTransfertShouldDebitAnAccountAndCreditAnotherOne() {
    account200.transfer(amount100, account100);
    assertThat(account200.getBalance(), is(equalTo(amount100)));
    assertThat(account100.getBalance(), is(equalTo(amount200)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void aTransfertWithANegativeAmountShouldFail() {
    account100.transfer(invalidAmount, account200);
  }

  @Test(expected = IllegalArgumentException.class)
  public void aTransfertWithAnAmountHigherThanTheBalanceShouldFail() {
    account100.transfer(amount200, account100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void aTransfertFromAnAccountToHimselfShouldFail() {
    account100.transfer(amount100, account100);
  }

}
