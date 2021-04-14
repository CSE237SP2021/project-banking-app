package main;
import java.math.BigDecimal;
import java.util.ArrayList;

import main.Transaction.transactionTypes;

public class Account {
    private BigDecimal balance = new BigDecimal(0);
    private long accountNumber = 0;
    private ArrayList<Transaction> transactionList;

    public Account() {
        // randomly generate accountNumber here later
    	transactionList = new ArrayList<Transaction>();
    }

    /**
     * increments the account balance by depositAmount
     * and addsa a new Transaction object to the transactionList ArrayList
     * 
     * @param depositAmount
     * @return the new account balance
     */
    public BigDecimal deposit(BigDecimal depositAmount) {
        this.balance = this.balance.add(depositAmount);
        
        transactionTypes depositType = transactionTypes.DEPOSIT;
        Transaction depositT = new Transaction(depositType, depositAmount, this.balance);
        transactionList.add(depositT);
        return this.balance;
    }

    /**
     * Decrements the account balance by withdrawAmount
     * and adds a new Transaction object to the transactionList ArrayList
     * 
     * @param withdrawAmount
     * @return the new account balance
     */
    public BigDecimal withdraw(BigDecimal withdrawAmount) {
        this.balance = this.balance.subtract(withdrawAmount);
        
        transactionTypes withdrawType = transactionTypes.WITHDRAW;
        Transaction withdrawT = new Transaction(withdrawType, withdrawAmount, this.balance);
        transactionList.add(withdrawT);
        
        return this.balance;
    }
    
    public BigDecimal getBalance() {
    	return this.balance;
    }
    
    public void printTransactionList() {
    	for(Transaction t : this.transactionList) {
    		System.out.println(t.toString());
    	}
    }
}
