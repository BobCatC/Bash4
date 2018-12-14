package domain.account;

import domain.client.Client;

public interface Account {
  Client getClient();

  void withdraw(double amount) throws IllegalArgumentException;
  void topUp(double amount) throws IllegalArgumentException;
  void transfer(double amount, Account account) throws IllegalArgumentException;

  void accrueInterest(Account targetAccount);
  void payCommission(Account targetAccount);
}