package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankSystem {
    private static BankSystem instance;
    private Map<String, BankAccount> accounts;
    private BankAccount loginAccount;

    public BankSystem() {
        this.accounts = new HashMap<>();
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
            loginAccount.getTransactions().add(new Transaction( Transaction.TransactionType.DEPOSIT, amount));
            FileIO.writeAccountToFile(this.getLoginAccount());
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
            loginAccount.getTransactions().add(new Transaction( Transaction.TransactionType.WITHDRAWAL, amount));
            FileIO.writeAccountToFile(this.getLoginAccount());
            return 1;
        }
        return 0;
    }

    public void addTask(Task task) {
        loginAccount.getTasks().add(task);
        FileIO.writeAccountToFile(loginAccount);
    }
    
    public void deleteTask(Task task) {
    	loginAccount.getTasks().remove(task);
        FileIO.writeAccountToFile(loginAccount);
    }

    public List<Task> getTasks() {
        return loginAccount.getTasks();
    }

    public List<Transaction> getTransactions() {
        return loginAccount.getTransactions();
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
