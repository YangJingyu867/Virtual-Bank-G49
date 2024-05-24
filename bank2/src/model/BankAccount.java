package model;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private String accountNumber;
    private String childPassword;
    private String parentPassword;
    private boolean isParent;
    private double money;
    private List<SavingsGoal> savingsGoals;
    private CurrentAccount current;
    private List<SavingsAccount> savingsList;
    private List<Task> tasks;
    private List<Transaction> transactions;

    public BankAccount(String accountNumber, String childPassword,String parentPassword) {
        this.accountNumber = accountNumber;
        this.childPassword = childPassword;
        this.parentPassword=parentPassword;
        this.isParent=false;
        this.savingsGoals = new ArrayList<>();
        this.current=new CurrentAccount(accountNumber);
        this.savingsList=new ArrayList<>();
        this.tasks = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    
    public String getchildPassword() {
        return childPassword;
    }
    
    public String getParentPassword() {
    	return parentPassword;
    }
    
    public List<Task> getTasks(){
    	return tasks;
    }
    
    public void setTasks(List<Task> tasks) {
    	this.tasks=tasks;
    }
    
    public List<Transaction> getTransactions(){
    	return transactions;
    }
    
    public void setTransactions(List<Transaction> transactions){
    	this.transactions=transactions;
    }
    
    public void setIsParent(boolean isParent) {
    	this.isParent=isParent;
    }
    
    public boolean isParent() {
    	return isParent;
    }
    
    public double getTotalBalance() {
    	double balance=0;
    	balance+=current.getBalance();
    	for(SavingsAccount savings:savingsList) {
    		balance+=savings.getBalance();
    	}
    	return balance;
    }

    public void updateGoals() {
    	for(SavingsGoal goal:savingsGoals) {
    		goal.update(getTotalBalance());
    	}
    }
    
    public void updateSavingsAccount() {
    	for(SavingsAccount account:savingsList) {
    		if(account.canWithdraw()) {
    			account.applyInterest();
    			depositCurrent(account.getBalance());
    			savingsList.remove(account);
    		}
    	}
    }
    
    public void depositCurrent(double amount) {
    	current.deposit(amount);
		updateGoals();
    }
    
    public void depositSavings(double amount,int month) {
    	SavingsAccount savings=new SavingsAccount(month);
    	savingsList.add(savings);
    	savings.deposit(amount);
		updateGoals();
    }
    
    public boolean withdrawCurrent(double amount) {
    	boolean b=current.withdraw(amount);
		updateGoals();
		return b;
    }
    
    public boolean withdrawSavings(double amount,SavingsAccount savings) {
    	boolean b=savings.withdraw(amount);
    	if(savings.getBalance()==0) {
    		savingsList.remove(savings);
    	}
		updateGoals();
		return b;
    }
    
    public void addSavingsGoal(SavingsGoal savingsGoal) {
        savingsGoals.add(savingsGoal);
        updateGoals();
        FileIO.writeAccountToFile(this);
    }

    public List<SavingsGoal> getSavingsGoals() {
        return savingsGoals;
    }
    
    public void setSavingsGoals(List<SavingsGoal> savingsGoals) {
        this.savingsGoals = savingsGoals;
    }

	public void removeGoal(SavingsGoal goal) {
		savingsGoals.remove(goal);
        FileIO.writeAccountToFile(this);
	}
	
	public CurrentAccount getCurrentAccount() {
		return current;
	}
	
	public List<SavingsAccount> getSavingsList(){
		return savingsList;
	}

	public void setSavingsList(List<SavingsAccount> savingsAccounts) {
		this.savingsList=savingsAccounts;
		
	}
	
	public double getMoney() {
		return money;
	}
	
	public void setMoney(double money) {
		this.money=money;
	}
}