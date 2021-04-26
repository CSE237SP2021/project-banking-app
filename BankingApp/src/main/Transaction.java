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
	 * format LocalDateTime to make transaction list more readable
	 */
	private String formatTime() {
		LocalDateTime dateTimeToFormat = LocalDateTime.now();
		return dateTimeToFormat.format(this.dateFormat);
	}

	public String getTimestamp() {
		return timestamp;
	}

	public transactionTypes getType() {
		return type;
	}

	public BigDecimal getAmount() {
		return amount;
	}
	
	public BigDecimal getNewBalance() {
		return this.newBalance;
	}

	@Override
	public String toString() {
		String transaction = timestamp + " : " + type + " of $" + amount + ".      BALANCE = $" + newBalance;
		return transaction;
	}
}
