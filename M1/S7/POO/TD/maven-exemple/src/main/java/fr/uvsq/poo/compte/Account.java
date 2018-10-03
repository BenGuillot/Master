package fr.uvsq.poo.compte;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

/**
 * La classe <code>Account</code> représente un compte bancaire.
 *
 * @author Stéphane Lopes
 * @version 2017
 */
class Account {
  private BigDecimal balance;

  /**
   * Crée un compte avec un montant initial.
   *
   * @param initialBalance le montant initial
   * @throws IllegalArgumentException si le montant initial est négatif
   */
  Account(BigDecimal initialBalance) {
    validateAmount(initialBalance);
    balance = initialBalance;
  }

  /**
   * Retourne le solde du compte.
   * @return le solde du compte
   */
  BigDecimal getBalance() {
    return balance;
  }

  /**
   * Crédite le compte.
   * @param amount le montant à créditer
   * @throws IllegalArgumentException si le montant à créditer est négatif
   */
  void credit(BigDecimal amount) {
    validateAmount(amount);
    balance = balance.add(amount);
  }

  /**
   * Débite le compte.
   * @param amount le montant à débiter
   * @throws IllegalArgumentException si le montant à débiter est négatif ou s'il est supérieur au solde
   */
  void debit(BigDecimal amount) {
    validateAmount(amount);
    if (balance.compareTo(amount) < 0) {
      throw new IllegalArgumentException("Montant supérieur au solde");
    }
    balance = balance.subtract(amount);
  }

  /**
   * Transfère une somme d'un compte à un autre.
   * @param amount le montant à transférer
   * @param targetAccount le compte destination
   * @throws IllegalArgumentException si le montant à transférer est négatif ou s'il est supérieur au solde du compte source
   */
  void transfer(BigDecimal amount, Account targetAccount) {
    if (targetAccount == this) {
      throw new IllegalArgumentException("Virement d'un compte sur lui-même");
    }
    debit(amount);
    targetAccount.credit(amount);
  }

  private static void validateAmount(BigDecimal amount) {
    if (amount.compareTo(ZERO) < 0) {
      throw new IllegalArgumentException("Montant invalide");
    }
  }
}
