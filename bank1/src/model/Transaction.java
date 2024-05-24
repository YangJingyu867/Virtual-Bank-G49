package model;

import java.time.LocalDateTime;

public class Transaction {
    public enum TransactionType {
        DEPOSIT, WITHDRAWAL
    }

    private BankAccount account;
    private TransactionType type;
    private double amount;
    private String description;

    public Transaction(BankAccount account, TransactionType type, double amount, String description) {
        this.account = account;
        this.type = type;
        this.amount = amount;
        this.description = description;
    }

    public BankAccount getAccount() {
        return account;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "account=" + account.getAccountNumber() +
                ", type=" + type +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}
