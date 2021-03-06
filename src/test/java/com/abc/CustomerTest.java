package com.abc;

import java.math.BigDecimal;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    @Test //Test customer statement generation
    public void testApp(){

        Account checkingAccount = new Checkings();
        Account savingsAccount = new Savings();

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(new BigDecimal(100.0));
        savingsAccount.deposit(new BigDecimal(4000.0));
        savingsAccount.withdraw(new BigDecimal(200.0));
        assertEquals("Statement for Henry\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "Total $100.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "Total $3,800.00\n" +
                "\n" +
                "Total In All Accounts $3,900.00", henry.getStatement());
    }

    @Test
    public void testOneAccount(){
        Customer oscar = new Customer("Oscar").openAccount(new Savings());
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar")
                .openAccount(new Savings());
        oscar.openAccount(new Checkings());
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Test
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar")
                .openAccount(new Savings());
        oscar.openAccount(new Checkings());
        oscar.openAccount(new MaxiSavings());
        assertEquals(3, oscar.getNumberOfAccounts());
    }
    
    @Test
    public void testTransfer(){
    	Customer oscar = new Customer("Oscar");
    	Account checkingAccount = new Checkings();
        Account savingsAccount = new Savings();
        
        oscar.openAccount(checkingAccount);
    	oscar.openAccount(savingsAccount);
    	
    	checkingAccount.deposit(new BigDecimal(1000));
    	savingsAccount.deposit(new BigDecimal(4000));
    	
    	oscar.transferBetweenAccounts(new BigDecimal(200), checkingAccount, savingsAccount);
    	System.out.println(oscar.getStatement());
    	assertEquals("Statement for Oscar\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $1,000.00\n" +
                "  withdrawal $200.00\n" +
                "Total $800.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  deposit $200.00\n" +
                "Total $4,200.00\n" +
                "\n" +
                "Total In All Accounts $5,000.00", oscar.getStatement());
    	
    	
    }
    
}

