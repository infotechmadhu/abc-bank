package com.abc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

    public String getName() {
        return name;
    }
   
    public Customer openAccount(Account account) {
        accounts.add(account);
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public BigDecimal totalInterestEarned() {
    	BigDecimal total = BigDecimal.ZERO;
        for (Account account : accounts)
        	total=total.add(account.interestEarned());
        return total;
    }
     
    /**
     * Generates transaction statement for a customer
     * @return statement string
     */
    public String getStatement() {
    	 StringBuffer statement = new StringBuffer();
         statement.append("Statement for ").append(name).append("\n");
         BigDecimal total = BigDecimal.ZERO;
         for (Account account : accounts) {
             statement.append("\n").append(statementForAccount(account)).append("\n");
             total = total.add(account.sumTransactions());
         }
         statement.append("\nTotal In All Accounts ").append(toDollars(total));
         return statement.toString();
    }

    private String statementForAccount(Account account) {
    	StringBuffer s = new StringBuffer("");
    	s.append(account.getAccountType());
    	
        //Now total up all the transactions
        BigDecimal total = BigDecimal.ZERO;
  
        for (Transaction transaction : account.getTransactions()) {
        	  s.append("  ").append(transaction.amount.compareTo(BigDecimal.ZERO) < 0?"withdrawal" : "deposit");
              s.append(" ").append(toDollars(transaction.amount)).append("\n");
              total=total.add(transaction.amount);
        }
       // s += "Total " + toDollars(total);
        s.append("Total ").append(toDollars(total));
        return s.toString();
    }

    private String toDollars(BigDecimal d){
        return String.format("$%,.2f", d.abs());
    }
    
    /**
     * Transfer the amount from one account to another
     * @param amount amount to be transferred
     * @param fromAccount the account to withdraw the amount
     * @param toAccount the account to deposit the amount
     */
    public void transferBetweenAccounts(BigDecimal amount,Account fromAccount,Account toAccount){
    	if (amount.compareTo(BigDecimal.ZERO) <= 0){
    		throw new IllegalArgumentException(Account.ERR_AMOUNT_LESS_THAN_ZERO);
    	}
    	if(fromAccount!=null && toAccount!=null){
    		fromAccount.withdraw(amount);
        	toAccount.deposit(amount);
    	}else{
    		throw new NullPointerException("Account doesnot exists");
    	}
    	
    	
    }
}
