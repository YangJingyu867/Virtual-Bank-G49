package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SavingsAccount {
    private double balance;
    private double interestRate = 0.05;
    private LocalDate creationDate;
    private int depositTermMonths; // Deposit term in months

    public SavingsAccount(int depositTermMonths) {
        this.balance = 0.0;
        this.creationDate = LocalDate.now();
        this.depositTermMonths = depositTermMonths;
        if(depositTermMonths==6) {
        	interestRate=3;
        }
        else if(depositTermMonths==12) {
        	interestRate=4;
        }
        else {
        	interestRate=5;
        }
    }

    public boolean withdraw(double amount) {
        if(!canWithdraw()) {
        	return false;
        }

        // If the withdrawal is allowed, proceed with withdrawal
        if (amount <= balance) {
            balance -= amount;
            return true; // Withdrawal successful
        } else {
            return false; // Insufficient funds
        }
    }
    
    public boolean canWithdraw() {
    	LocalDate withdrawalDate = LocalDate.now();
        LocalDate withdrawalAllowedDate = creationDate.plusMonths(depositTermMonths);

        // Check if the current date is after the allowed withdrawal date
        if (withdrawalDate.isBefore(withdrawalAllowedDate)) {
            return false; // Withdrawal not allowed until deposit term is over
        }
        return true;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void applyInterest() {
        // Apply interest to the balance
        balance += balance * interestRate * depositTermMonths;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getDepositTermMonths() {
        return depositTermMonths;
    }

    public void setDepositTermMonths(int depositTermMonths) {
        this.depositTermMonths = depositTermMonths;
    }
    
    @Override
    public String toString() {
        // 使用 DateTimeFormatter 格式化日期
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return "Savings Account: balance: " + balance
                + " Creation Date: " + creationDate.format(formatter) 
                + " Term Months: " + depositTermMonths;
    }
}
