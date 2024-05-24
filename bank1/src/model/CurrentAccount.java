package model;

public class CurrentAccount {
    private String accountNumber;
	private double balance;
    private double interestRate = 0.02; 

    public CurrentAccount(String accountNumber) {
    	this.accountNumber=accountNumber;
        this.balance = 0.0;
    }
    
    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }
    
    public void setBalance(double balance) {
    	this.balance=balance;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true; // Withdrawal successful
        } else {
            return false; // Incorrect password or insufficient funds
        }
    }

    public void applyInterest() {
        balance += balance * interestRate;
    }
    
    @Override
    public String toString() {
        return "Current Account: " + accountNumber +" balance: " + balance;
    }
}