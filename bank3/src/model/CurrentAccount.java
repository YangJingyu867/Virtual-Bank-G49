package model;

public class CurrentAccount {
    private String accountNumber;
	private double balance;

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
    
    @Override
    public String toString() {
        return "Current Account: " + accountNumber +" balance: " + String.format("%.2f$", balance);
    }
}