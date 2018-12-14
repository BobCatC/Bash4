package domain.account;

import domain.client.Client;

public class CurrentAccount extends AccountTemplate {

  private final double constInterest;

  CurrentAccount(Client client, double constInterest) {
    super(client);
    this.constInterest = constInterest;
  }

  @Override
  protected boolean withdrawShouldBegin(double amount) {
    return amount <= value;
  }

  @Override
  protected boolean topUpShouldBegin(double amount) {
    return true;
  }

  @Override
  protected boolean transferShouldBegin(double amount, Account account) {
    Client targetClient = account.getClient();
    return withdrawShouldBegin(amount) && client.equals(targetClient);
  }


  @Override
  public void accrueInterest(Account targetAccount) {
    if (targetAccount == this) {
      value += constInterest;
    } else if (next != null) {
      next.accrueInterest(targetAccount);
    }
  }

  @Override
  public void payCommission(Account targetAccount) {
    if (targetAccount != this && next != null) {
      next.payCommission(targetAccount);
    }
  }
}
