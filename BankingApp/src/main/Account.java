package main;
import java.math.BigDecimal;

public class Account {
    private BigDecimal balance = new BigDecimal(0);
    private long accountNumber = 0;

    public Account() {
        // randomly generate accountNumber here later
    }

    /**
     * increments the account balance by depositAmount
     * 
     * @param depositAmount
     * @return the new account balance
     */
    public BigDecimal deposit(BigDecimal depositAmount) {
        this.balance = this.balance.add(depositAmount);
        return this.balance;
    }

    /**
     * Decrements the account balance by withdrawAmount
     * 
     * @param withdrawAmount
     * @return the new account balance
     */
    public BigDecimal withdraw(BigDecimal withdrawAmount) {
        this.balance = this.balance.subtract(withdrawAmount);
        return this.balance;
    }
    
    /**
     * Gets value of balance
     * 
     * @return value of balance
     */
    public BigDecimal getBalance() {
    	return this.balance;
    }
}
