package com.abc;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class Transaction {
    public final BigDecimal amount;

    private Date transactionDate;


    public Transaction(BigDecimal amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
    }
   
     /**
     * Get transaction date
     * @return date
     */
    public Date getTransactionDate() {
		return transactionDate;
	}
    
    /**
     * Get transaction amount
     * @return amount
     */
	public BigDecimal getAmount() {
		return amount;
	}
    
    

}
