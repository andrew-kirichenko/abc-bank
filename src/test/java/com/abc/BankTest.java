package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest {

    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new CheckingAccount());
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new CheckingAccount();
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        bill.depositFunds(checkingAccount, 100.0);

        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account() {
        Bank bank = new Bank();
        Account savingsAccount = new SavingsAccount();
        Customer bill = new Customer("Bill").openAccount(savingsAccount);
        bank.addCustomer(bill);

        bill.depositFunds(savingsAccount, 1500.0);

        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        Account maxiSavingsAccount = new MaxiSavingsAccount();
        Customer bill = new Customer("Bill").openAccount(maxiSavingsAccount);
        bank.addCustomer(bill);

        bill.depositFunds(maxiSavingsAccount, 3000.0);

        assertEquals(150.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void withdrawalInLast10daysForMaxiSavingsAccount() {
        Bank bank = new Bank();
        Account maxiSavingsAccount = new MaxiSavingsAccount();
        Customer bill = new Customer("Bill").openAccount(maxiSavingsAccount);
        bank.addCustomer(bill);

        bill.depositFunds(maxiSavingsAccount, 3000.0);
        bill.withdrawFunds(maxiSavingsAccount, 2000.0);

        assertEquals(1.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
}
