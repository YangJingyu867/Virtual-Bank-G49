package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankSystem {
    private static BankSystem instance;
    private Map<String, BankAccount> accounts;
    private List<Transaction> transactions;
    private List<Task> tasks;
    private BankAccount loginAccount;

    public BankSystem() {
        this.accounts = new HashMap<>();
        this.transactions = new ArrayList<>();
        this.tasks = new ArrayList<>();
        loadDataFromFiles();
    }
    public static BankSystem getInstance() {
        if (instance == null) {
            instance = new BankSystem();
        }
        return instance;
    }
    
    public void loadDataFromFiles() {
        accounts = FileIO.readAccountsFromFile();
        transactions = FileIO.readTransactionsFromFile(accounts);
        tasks = FileIO.readTasksFromFile();
    }

    public int createAccount(String accountNumber, String childPassword,String parentPassword) {
    	if(accountNumber.length()<7) {
    		return 2;
    	}
    	else if(childPassword.length()!=6||parentPassword.length()!=6) {
    		return 3;
    	}
        BankAccount account= new BankAccount(accountNumber, childPassword,parentPassword);
        accounts.put(accountNumber, account);
        
        FileIO.writeAccountToFile(account);
        return 1;
    }

    public boolean authenticate(String accountNumber, String password) {
        BankAccount account = accounts.get(accountNumber);
        if(account==null) {
        	return false;
        }
        if(account.getParentPassword().equals(password)) {
        	account.setIsParent(true);
        	return true;
        }
        else if(account.getchildPassword().equals(password)) {
        	account.setIsParent(false);
        	return true;
        }
        else {
        	return false;
        }
    }

    public int deposit(String accountNumber, double amount,int month) {
        BankAccount account = loginAccount;
        if (amount <= 0) {
            return 2; 
        } else if (account != null && amount > account.getMoney()) {
            return 3; 
        } else if (account != null) {
        	if(month==0) {
        		account.depositCurrent(amount);
        	}
        	else {
        		account.depositSavings(amount,month);
        	}
            transactions.add(new Transaction(account, Transaction.TransactionType.DEPOSIT, amount, "Deposit to " + accountNumber));
            FileIO.writeAccountToFile(this.getLoginAccount());
            FileIO.writeTransactionToFile(new Transaction(account, Transaction.TransactionType.DEPOSIT, amount, "Deposit to " + accountNumber));
            return 1;
        }
        return 0; 
    }

    public int withdraw(double amount,SavingsAccount savings) {
        BankAccount account = loginAccount;
        if (amount <= 0) {
            return 2; 
        } else if ((savings==null&&account.getCurrentAccount().getBalance()<amount)||(savings!=null&&savings.getBalance()<amount)) {
            return 3; 
        } else if (account != null) {
        	if((savings==null&&!account.getCurrentAccount().withdraw(amount))||(savings!=null&&!savings.withdraw(amount))) {
        		return 4;
        	}
            transactions.add(new Transaction(account, Transaction.TransactionType.WITHDRAWAL, amount, "Withdrawal from " + loginAccount.getAccountNumber()));
            FileIO.writeAccountToFile(this.getLoginAccount());
            FileIO.writeTransactionToFile(new Transaction(account, Transaction.TransactionType.WITHDRAWAL, amount, "Withdrawal from " + loginAccount.getAccountNumber()));
            return 1;
        }
        return 0;
    }

    public void addTask(Task task) {
        tasks.add(task);
        FileIO.writeTaskToFile(tasks);
    }
    
    public void deleteTask(Task task) {
        tasks.remove(task);
        FileIO.writeTaskToFile(tasks);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public Map<String, BankAccount> getAccounts() {
        return accounts;
    }
    
    public BankAccount getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }
    
    public double getMoney() {
    	return loginAccount.getMoney();
    }
    
    public void setMoney(double money) {
    	loginAccount.setMoney(money);
    	FileIO.writeAccountToFile(loginAccount);
    }
    
	public void setLoginAccount(BankAccount loginAccount) {
		this.loginAccount=loginAccount;
        this.loginAccount.updateGoals();
	}
	
	public BankAccount getLoginAccount() {
		return loginAccount;
	}
}
