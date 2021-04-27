package main;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
	
	public enum transactionTypes {
		DEPOSIT,
		WITHDRAW,
		TRANSFER
	}
	
	private String timestamp;
	private transactionTypes type;
	private BigDecimal amount;
	private BigDecimal newBalance;
	
	//Formatting borrowed from https://www.w3schools.com/java/java_date.asp
	private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	
	/**
	 * Transaction Constructor
	 * 
	 * Includes function call to formatTime()
	 * 
	 * @param type
	 * @param amount
	 */
	public Transaction(transactionTypes type, BigDecimal amount, BigDecimal newBalance) {
		this.newBalance = newBalance;
		this.amount = amount;
		this.type = type;
		this.timestamp = formatTime();
	}
	
	/**
	 * Transaction Constructor with timestamp
	 * 
	 * Includes function call to formatTime()
	 * 
	 * @param type
	 * @param amount
	 */
	public Transaction(String timestamp, transactionTypes type, BigDecimal amount, BigDecimal newBalance) {
		this.newBalance = newBalance;
		this.amount = amount;
		this.type = type;
		this.timestamp = timestamp;
	}

	/**
	 * format LocalDateTime to make transaction list more readable
	 */
	private String formatTime() {
		LocalDateTime dateTimeToFormat = LocalDateTime.now();
		return dateTimeToFormat.format(this.dateFormat);
	}

	public String getTimestamp() {
		return this.timestamp;
	}

	public transactionTypes getType() {
		return this.type;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}
	
	public BigDecimal getNewBalance() {
		return this.newBalance;
	}

	@Override
	public String toString() {
		String transaction = this.timestamp + " : " + this.type + " of $" + this.amount + ".      BALANCE = $" + this.newBalance;
		return transaction;
	}
}
