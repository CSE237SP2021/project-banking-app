package main;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import main.Transaction.transactionTypes;

public class Account {
    private BigDecimal balance = new BigDecimal(0);
    private String accountName;
    private ArrayList<Transaction> transactionList;

    public Account(String accountName) {
    	this.accountName = accountName;
    	this.transactionList = new ArrayList<Transaction>();
    }
    
    public Account(String accountName, BigDecimal balance, ArrayList<Transaction> transactions) {
    	this.accountName = accountName;
    	this.balance = balance;
    	this.transactionList = transactions;
    }

    /**
     * increments the account balance by depositAmount
     * and addsa a new Transaction object to the transactionList ArrayList
     * 
     * @param depositAmount
     * @return the new account balance
     */
    public BigDecimal deposit(BigDecimal depositAmount) {
    	if (depositAmount.compareTo(new BigDecimal(0)) > 0) {
            this.balance = this.balance.add(depositAmount);
            
            transactionTypes depositType = transactionTypes.DEPOSIT;
            Transaction depositT = new Transaction(depositType, depositAmount, this.balance);
            transactionList.add(depositT);
            
            this.updateBalanceFile();
    	}

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
    	if (withdrawAmount.compareTo(new BigDecimal(0)) > 0) {
            this.balance = this.balance.subtract(withdrawAmount);
            
            transactionTypes withdrawType = transactionTypes.WITHDRAW;
            Transaction withdrawT = new Transaction(withdrawType, withdrawAmount, this.balance);
            transactionList.add(withdrawT);
            
            this.updateBalanceFile();
    	}

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

    /**
     * 
     * @return accountName
     */
    public String getAccountName() {
    	return this.accountName;
    }
    
    public String toString() {
    	return this.accountName;

    }
    
    /*
     * Updates the balance.txt file contained within the account directory
     */
    private void updateBalanceFile() {
    	//only attempt to write to a file if we navigated here through a menu to avoid breaking tests
    	if (AuthMenu.currentPersonName != null && !AuthMenu.currentPersonName.isBlank()) {
        	try {
    			FileWriter balanceWriter = new FileWriter(Auth.BASE_PATH + "/" + AuthMenu.currentPersonName + "/" + this.accountName + Auth.BALANCE_NAME);
    			balanceWriter.write(this.balance.toString());
    			balanceWriter.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}

    }
}
