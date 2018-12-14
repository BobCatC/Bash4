import domain.account.Account;
import domain.account.AccountFabric;
import domain.client.Client;

import java.util.Date;

public class Main {

  public static void main(String[] args) {

    Client me = Client.builder().setAddress("SPb").setFirstName("Aleksandr").setLastName("Pakhomov").setPassportId("4012 362948").build();
    Client rafa = Client.builder().setAddress("Вязьма").setFirstName("Rafael").setLastName("Yakupov").build();

    AccountFabric fabric = new AccountFabric(0.02, 0.04, 20, 1000, 10000);

    Account myCurrent = fabric.current(me);
    myCurrent.topUp(100);
    myCurrent.withdraw(10);

    Account myDeposit = fabric.deposit(me, 2000, new Date(2018,10,10));
    myDeposit.withdraw(100);
    myDeposit.topUp(1000);
    myDeposit.transfer(900, myCurrent);

    myCurrent.payCommission(myDeposit);
    myCurrent.accrueInterest(myDeposit);

    Account myCredit = fabric.credit(me);
    myCredit.withdraw(10);

    myCurrent.payCommission(myCredit);

    myCredit.withdraw(1000);
    myCredit.topUp(10000);

    myCurrent.payCommission(myCredit);

    myCredit.transfer(10000, myCurrent);
    myCredit.transfer(1000, myDeposit);
  }

}
