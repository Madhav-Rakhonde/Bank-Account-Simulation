import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Account {
    private String accountHolderName;
    private String accountNumber;
    private String pin;
    private double balance;
    private List<String> transactionHistory;
    private static final int HISTORY_LIMIT = 10;

    public Account(String name, String accNo, String pin, double initialBalance) {
        this.accountHolderName = name;
        this.accountNumber = accNo;
        this.pin = pin;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        addToHistory(" Account created with balance: ₹" + initialBalance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public boolean validatePin(String inputPin) {
        return this.pin.equals(inputPin);
    }

    public double getBalance() {
        return balance;
    }

    private String getTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private void addToHistory(String entry) {
        if (transactionHistory.size() == HISTORY_LIMIT) {
            transactionHistory.remove(0);
        }
        transactionHistory.add("[" + getTimestamp() + "] " + entry);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println(" Deposit amount must be positive.");
            addToHistory(" Failed deposit attempt: ₹" + amount);
            return;
        }
        balance += amount;
        System.out.println(" Deposited ₹" + amount);
        addToHistory("Deposited: ₹" + amount);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println(" Withdrawal amount must be positive.");
            addToHistory(" Failed withdrawal attempt: ₹" + amount);
            return;
        }
        if (amount > balance) {
            System.out.println(" Insufficient balance.");
            addToHistory(" Failed withdrawal: ₹" + amount + " (Insufficient balance)");
            return;
        }
        balance -= amount;
        System.out.println(" Withdrew ₹" + amount);
        addToHistory("Withdrew: ₹" + amount);
    }

    public void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println(" No transactions yet.");
            return;
        }
        System.out.println("\n Last " + transactionHistory.size() + " Transactions:");
        for (String txn : transactionHistory) {
            System.out.println(" - " + txn);
        }
    }

    public void showAccountDetails() {
        System.out.println("\n Account Details:");
        System.out.println("Name           : " + accountHolderName);
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Balance        : ₹" + balance);
    }

    public List<String> toFileString() {
        List<String> lines = new ArrayList<>();
        lines.add("BEGIN_ACCOUNT");
        lines.add("Name:" + accountHolderName);
        lines.add("Account:" + accountNumber);
        lines.add("PIN:" + pin);
        lines.add("Balance:" + balance);
        for (String txn : transactionHistory) {
            lines.add("Txn:" + txn);
        }
        lines.add("END_ACCOUNT");
        return lines;
    }

    public static Account fromFileLines(List<String> lines) {
        String name = "", accNo = "", pin = "";
        double balance = 0;
        List<String> txns = new ArrayList<>();

        for (String line : lines) {
            if (line.startsWith("Name:")) name = line.substring(5);
            else if (line.startsWith("Account:")) accNo = line.substring(8);
            else if (line.startsWith("PIN:")) pin = line.substring(4);
            else if (line.startsWith("Balance:")) balance = Double.parseDouble(line.substring(8));
            else if (line.startsWith("Txn:")) txns.add(line.substring(4));
        }

        Account acc = new Account(name, accNo, pin, balance);
        acc.transactionHistory.clear();
        acc.transactionHistory.addAll(txns);
        return acc;
    }
}
