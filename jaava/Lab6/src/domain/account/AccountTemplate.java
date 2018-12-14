package domain.account;

import domain.client.Client;

abstract class AccountTemplate implements Account {

  protected final Client client;
  protected double value;
  protected Account next;


  AccountTemplate(Client client) {
    this.client = client;
    value = 0;
  }

  public Client getClient() {
    return client;
  }

  public void withdraw(double amount) throws IllegalArgumentException {
    if (!withdrawShouldBegin(amount)) {
      throw new IllegalArgumentException();
    }
    value -= amount;
  }

  public void topUp(double amount) throws IllegalArgumentException {
    if (!topUpShouldBegin(amount)) {
      throw new IllegalArgumentException();
    }
    value += amount;
  }

  public void transfer(double amount, Account account) throws IllegalArgumentException {
    if (!transferShouldBegin(amount, account)) {
      throw new IllegalArgumentException();
    }
    withdraw(amount);
    account.topUp(amount);
  }


  void addAccountIntoChain(AccountTemplate account) {
    if (next == this) {
      next = account;
      account.next = this;
    } else {
      account.next = next;
      next = account;
    }
  }


  protected abstract boolean withdrawShouldBegin(double amount);
  protected abstract boolean topUpShouldBegin(double amount);
  protected abstract boolean transferShouldBegin(double amount, Account account);

}

