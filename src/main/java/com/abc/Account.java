package com.abc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class Account {

    public List<Transaction> transactions;
    public static final String ERR_AMOUNT_LESS_THAN_ZERO = "Amount must be greater than zero";
    private boolean isDeposit;//flag to set the transaction
   
	public Account() {
        this.transactions = new ArrayList<Transaction>();
    }
     
	/**
	 * Adds a transaction for deposit
	 * @param amount the amount to be deposited
	 */
    public void deposit(BigDecimal amount) {
    	if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(ERR_AMOUNT_LESS_THAN_ZERO);
        } else {
        	this.setDeposit(true);
            transactions.add(new Transaction(amount));
        }
    }
    
  /**
   * Adds a transaction for withdrawl
   * @param amount the amount to withdraw
   */
   public void withdraw(BigDecimal amount) {
	   if (amount.compareTo(BigDecimal.ZERO) <= 0) {
        throw new IllegalArgumentException(ERR_AMOUNT_LESS_THAN_ZERO);
    } else {
    	this.setDeposit(false);
        transactions.add(new Transaction(amount.negate()));
    }
  }
   
 
   public abstract BigDecimal interestEarned();
    
   /**
    * Checks if the transaction exists.
    * @return amount.
    */
    public BigDecimal sumTransactions() {
       return checkIfTransactionsExist(true);
    }

    private BigDecimal checkIfTransactionsExist(boolean checkAll) {
        BigDecimal amount = BigDecimal.ZERO;
        for (Transaction transaction: transactions)
        	amount = amount.add(transaction.amount);
        return amount;
    }
    
    public abstract String getAccountType();
    
    
    /**
     * Get the transaction flag
     * @return isDeposit flag 
     */
	public boolean isDeposit() {
		return isDeposit;
	}

	/**
	 * Set the transaction flag
	 * @param isDeposit
	 */
	public void setDeposit(boolean isDeposit) {
		this.isDeposit = isDeposit;
	}
    


  
}
