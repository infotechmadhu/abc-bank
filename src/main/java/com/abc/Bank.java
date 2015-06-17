package com.abc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Customer> customers;

    public Bank() {
        customers = new ArrayList<Customer>();
    }
    
    /**
     * Add a customer object.
     * @param customer
     */
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
    
    /**
     * Creates customer summary
     * @return summary
     */
    public String customerSummary() {
    	   StringBuffer summary = new StringBuffer("Customer Summary");
    	   for (Customer c : customers) {
               summary.append("\n - ").append(c.getName());
           	   summary.append(" (");
               summary.append(format(c.getNumberOfAccounts(), "account"));
               summary.append(")");
           } 
        return summary.toString();
    }

    //Make sure correct plural of word is created based on the number passed in:
    //If number passed in is 1 just return the word otherwise add an 's' at the end
    private String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }
    
    /**
     * Calculates the interest paid by customer.
     * @return total. the total interest paid
     */
    public BigDecimal totalInterestPaid() {
    	BigDecimal total = BigDecimal.ZERO;
        for(Customer customer: customers)
           // total += c.totalInterestEarned();
        	total=total.add(customer.totalInterestEarned());
        return total;
    }
    
    /**
     * Get first customer
     * @return first customer name
     */
    public String getFirstCustomer() {
        try {
            customers = null;
            return customers.get(0).getName();
        } catch (Exception e){
            e.printStackTrace();
            return "Error";
        }
    }
}
