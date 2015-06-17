package com.abc;



import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;
    private Bank bank;
    
    @Before
    public void createBankObj(){
    	 bank = new Bank();
    	
    }

    @Test
    public void customerSummary() {
        Customer john = new Customer("John");
        john.openAccount(new Checkings());
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
        Account checkingAccount = new Checkings();
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(new BigDecimal(100.0));

        assertEquals(0.1, bank.totalInterestPaid().doubleValue(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account() {
        Account savingsAccount = new Savings();
        bank.addCustomer(new Customer("Bill").openAccount(savingsAccount));
        savingsAccount.deposit(new BigDecimal(1500.0));

        assertEquals(2.0, bank.totalInterestPaid().doubleValue(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() {
        Account checkingAccount = new MaxiSavings();
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(new BigDecimal(3000.0));
        assertEquals(150.0, bank.totalInterestPaid().doubleValue(), DOUBLE_DELTA);
    }
    
    @Test
    public void withdrawlInlast10days(){
        Account maxiSavingsAccount = new MaxiSavings();
        bank.addCustomer(new Customer("Bill").openAccount(maxiSavingsAccount));
        maxiSavingsAccount.deposit(new BigDecimal(3000.0));
        maxiSavingsAccount.withdraw(new BigDecimal(200));
        System.out.println(bank.totalInterestPaid().doubleValue());
        assertEquals(2.80, bank.totalInterestPaid().doubleValue(), DOUBLE_DELTA);
          	
    }
    
   

}
