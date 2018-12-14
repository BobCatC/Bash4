package domain.account;

import domain.client.Client;

import java.util.Date;

public class AccountFabric {

  private final double constInterest;
  private final double depositCoefficient;
  private final double creditCommission;
  private final double maxAmountSuspicious;
  private final double creditLimit;

  private AccountTemplate anyAccountInChain;

  public AccountFabric(double constInterest, double depositCoefficient, double creditCommission, double maxAmountSuspicious, double creditLimit) {
    this.constInterest = constInterest;
    this.depositCoefficient = depositCoefficient;
    this.creditCommission = creditCommission;
    this.maxAmountSuspicious = maxAmountSuspicious;
    this.creditLimit = creditLimit;
  }

  public Account current(Client client) {
    return checkClient(addIntoChain(new CurrentAccount(client, constInterest)));
  }

  public Account deposit(Client client, double amount, Date expireDate) {
    return checkClient(addIntoChain(new DepositAccount(client, amount, expireDate, Math.pow(amount, depositCoefficient))));
  }

  public Account credit(Client client) {
    return checkClient(addIntoChain(new CreditAccount(client, creditCommission, creditLimit)));
  }

  private Account addIntoChain(AccountTemplate newAccount) {
    if (anyAccountInChain == null) {
      anyAccountInChain = newAccount;
      newAccount.next = newAccount;
    } else {
      anyAccountInChain.addAccountIntoChain(newAccount);
    }
    return newAccount;
  }

  private Account checkClient(Account newAccount) {
    Client client = newAccount.getClient();
    if (client.addressNotSet() || client.passportIdNotSet()) {
      return new SuspiciousAccount(newAccount, maxAmountSuspicious);
    }
    return newAccount;
  }
}
