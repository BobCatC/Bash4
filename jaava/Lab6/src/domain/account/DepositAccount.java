package domain.account;

import domain.client.Client;

import java.util.Date;

public class DepositAccount extends AccountTemplate {

  private final Date expireDate;
  private final double percent;


  DepositAccount(Client client, double amount, Date expireDate, double percent) {
    super(client);
    this.expireDate = expireDate;
    this.percent = percent;
    value = amount;
  }

  @Override
  protected boolean withdrawShouldBegin(double amount) {
    System.out.println(expireDate.after(new Date()));
    return expireDate.after(new Date()) && amount <= value;
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
      value *= (1 + 0.01 * percent);
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
