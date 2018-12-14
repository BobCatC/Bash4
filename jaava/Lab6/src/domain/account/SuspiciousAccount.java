package domain.account;


import domain.client.Client;

public class SuspiciousAccount implements Account {

  private final Account account;
  private final double maxAmount;

  SuspiciousAccount(Account account, double maxAmount) {
    this.account = account;
    this.maxAmount = maxAmount;
  }

  @Override
  public Client getClient() {
    return account.getClient();
  }

  @Override
  public void withdraw(double amount) throws IllegalArgumentException {
    if (amount > maxAmount) {
      throw new IllegalArgumentException("Too much for suspicious account");
    }
    account.withdraw(amount);
  }

  @Override
  public void topUp(double amount) throws IllegalArgumentException {
    account.topUp(amount);
  }

  @Override
  public void transfer(double amount, Account account) throws IllegalArgumentException {
    if (amount > maxAmount) {
      throw new IllegalArgumentException("Too much for suspicious account");
    }
    account.transfer(amount, account);
  }

  @Override
  public void accrueInterest(Account targetAccount) {
    account.accrueInterest(targetAccount);
  }

  @Override
  public void payCommission(Account targetAccount) {
    account.payCommission(targetAccount);
  }
}
