package domain.account;

import domain.client.Client;

public class CreditAccount extends AccountTemplate {

  private final double commission;
  private final double limit;

  CreditAccount(Client client, double commission, double limit) {
    super(client);
    this.commission = commission;
    this.limit = limit;
  }


  @Override
  protected boolean withdrawShouldBegin(double amount) {
    return (value - commission - amount) >= -limit;
  }

  @Override
  protected boolean topUpShouldBegin(double amount) {
    return true;
  }

  @Override
  protected boolean transferShouldBegin(double amount, Account account) {
    Client targetClient = account.getClient();
    return client.equals(targetClient);
  }



  @Override
  public void accrueInterest(Account targetAccount) {
    if (targetAccount != this && next != null) {
      next.accrueInterest(targetAccount);
    }
  }

  @Override
  public void payCommission(Account targetAccount) {
    if (targetAccount == this) {
      if (value < 0) {
        value -= commission;
      }
    } else  if (next != null) {
      next.payCommission(targetAccount);
    }
  }
}
