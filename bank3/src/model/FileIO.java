package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileIO {
    public static void writeLinesToFile(List<String> lines, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,false))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> readLinesFromFile(String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
    
    public static void writeAccountToFile(BankAccount account) {
        String fileName = account.getAccountNumber() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {
            writer.write("AccountNumber: " + account.getAccountNumber());
            writer.newLine();
            writer.write("ParentPassword: " + account.getParentPassword());
            writer.newLine();
            writer.write("ChildPassword: " + account.getchildPassword());
            writer.newLine();
            writer.write("CurrentBalance: " + account.getCurrentAccount().getBalance());
            writer.newLine();
            writer.write("Money: "+account.getMoney());
            writer.newLine();
            writer.write("SavingsAccounts: ");
            writer.newLine();
            for (SavingsAccount savingsAccount : account.getSavingsList()) {
                writer.write("Balance: " + savingsAccount.getBalance());
                writer.newLine();
                writer.write("CreationDate: " + savingsAccount.getCreationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                writer.newLine();
                writer.write("TermMonths: " + savingsAccount.getDepositTermMonths());
                writer.newLine();
            }
            writer.write("SavingsGoals: ");
            writer.newLine();
            for (SavingsGoal savingsGoal : account.getSavingsGoals()) {
                writer.write("Description: " + savingsGoal.getDescription());
                writer.newLine();
                writer.write("TargetAmount: " + savingsGoal.getTargetAmount());
                writer.newLine();
            }

            writer.write("Tasks: ");
            writer.newLine();
            for (Task task : account.getTasks()) {
                writer.write("Task Description: " + task.getDescription());
                writer.newLine();
                writer.write("Reward: " + task.getReward());
                writer.newLine();
            }
            
            writer.write("Transcations: ");
            writer.newLine();
            for(Transaction transaction:account.getTransactions()) {
                writer.write("Type: "+transaction.getType());
                writer.newLine();
                writer.write("Amount: "+transaction.getAmount());
                writer.newLine();
                writer.write("Date: "+transaction.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                writer.newLine();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

public static Map<String, BankAccount> readAccountsFromFile() {
    Map<String, BankAccount> accounts = new HashMap<>();
    try {
        // 遍历文件以加载账户数据
        File folder = new File(".");
        for (File file : folder.listFiles()) {
            if (file.getName().endsWith(".txt") && !file.getName().equals("transactions.txt") && !file.getName().equals("tasks.txt")) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    String accountNumber = null;
                    String childPassword = null;
                    String parentPassword = null;
                    double currentBalance = 0.0,money=0.0;
                    List<SavingsAccount> savingsAccounts = new ArrayList<>();
                    List<SavingsGoal> savingsGoals = new ArrayList<>();
                    List<Task> tasks=new ArrayList<>();
                    List<Transaction> transactions=new ArrayList<>();
                    LocalDate creationDate = null;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(":");
                        if (parts.length == 2) {
                            String key = parts[0].trim();
                            String value = parts[1].trim();
                            if (key.equals("AccountNumber")) {
                                accountNumber = value;
                                continue;
                            } else if (key.equals("ParentPassword")) {
                            	parentPassword = value;
                                continue;
                            } else if(key.equals("ChildPassword")){
                                childPassword = value;
                                continue;
                            } else if (key.equals("CurrentBalance")) {
                                currentBalance = Double.parseDouble(value);
                                continue;
                            }else if(key.equals("Money")) {
                            	money=Double.parseDouble(value);
                            } else if (key.equals("Balance")) {
                            	double balance = Double.parseDouble(value);
                            	line = reader.readLine();
                                LocalDate currentDate = LocalDate.parse(line.split(":")[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                                line = reader.readLine();
                                int depositTermMonths = Integer.parseInt(line.split(":")[1].trim());
                                SavingsAccount savingsAccount = new SavingsAccount(depositTermMonths);
                                savingsAccount.setCreationDate(currentDate);
                                savingsAccount.setBalance(balance);
                                savingsAccounts.add(savingsAccount);
                                continue;
                            } else if (key.equals("Description")) {
                                String goalDescription = value;
                                line = reader.readLine();
                                double targetAmount = Double.parseDouble(line.split(":")[1].trim());
                                SavingsGoal savingsGoal = new SavingsGoal(goalDescription, targetAmount);
                                savingsGoals.add(savingsGoal);
                                continue;
                            } else if(key.equals("Task Description")) {
                            	String taskDescription=value;
                            	line=reader.readLine();
                            	double reward=Double.parseDouble(line.split(":")[1].trim());
                            	Task task=new Task(taskDescription,reward);
                            	tasks.add(task);
                            }else if(key.equals("Type")) {
                            	Transaction.TransactionType type=Transaction.TransactionType.valueOf(value);
                            	line=reader.readLine();
                            	double amount=Double.parseDouble(line.split(":")[1].trim());
                            	line = reader.readLine();
                                LocalDate date = LocalDate.parse(line.split(":")[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                            	Transaction transaction=new Transaction(type,amount);
                            	transaction.setDate(date);
                            	transactions.add(transaction);
                            }
                        }
                    }
                    BankAccount account = new BankAccount(accountNumber, childPassword, parentPassword);
                    account.getCurrentAccount().setBalance(currentBalance);
                    account.setMoney(money);
                    account.setSavingsList(savingsAccounts);
                    account.setSavingsGoals(savingsGoals);
                    account.setTasks(tasks);
                    account.setTransactions(transactions);
                    accounts.put(accountNumber, account);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    } catch (NullPointerException e) {
        e.printStackTrace();
    }
    return accounts;
}

}
