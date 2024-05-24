package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction {
    public enum TransactionType {
        DEPOSIT, WITHDRAWAL
    }
    
    private TransactionType type;
    private double amount;
    private LocalDate date;

    public Transaction(TransactionType type, double amount) {
        this.type = type;
        this.amount = amount;
        this.date=LocalDate.now();
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
    @Override
    public String toString() {
        return "Transaction Type:" + type +" Amount: " + amount+" Date: "+date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date=date;
	}
}
