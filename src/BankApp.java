import java.util.*;

public class BankApp {
    private static Map<String, Account> accounts;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        accounts = FileManager.loadAccounts();
        System.out.println(" Welcome to Java Bank!");

        while (true) {
            try {
                System.out.println("\n==== MAIN MENU ====");
                System.out.println("1. Register New Account");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                int choice = getIntInput();

                switch (choice) {
                    case 1 -> registerAccount();
                    case 2 -> login();
                    case 3 -> {
                        FileManager.saveAccounts(accounts);
                        System.out.println(" Thank you for banking with us!");
                        return;
                    }
                    default -> System.out.println(" Invalid option.");
                }
            } catch (Exception e) {
                System.out.println(" Unexpected error: " + e.getMessage());
            }
        }
    }

    private static void registerAccount() {
        try {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();

            System.out.print("Enter account number: ");
            String accNo = scanner.nextLine();

            if (accounts.containsKey(accNo)) {
                System.out.println(" Account number already exists.");
                return;
            }

            System.out.print("Set a 4-digit PIN: ");
            String pin = scanner.nextLine();
            if (!pin.matches("\\d{4}")) {
                System.out.println(" PIN must be 4 digits.");
                return;
            }

            System.out.print("Enter initial balance: ₹");
            double balance = getDoubleInput();

            Account acc = new Account(name, accNo, pin, balance);
            accounts.put(accNo, acc);
            FileManager.saveAccounts(accounts);
            System.out.println(" Account registered successfully.");
        } catch (Exception e) {
            System.out.println(" Error during registration: " + e.getMessage());
        }
    }

    private static void login() {
        try {
            System.out.print("Enter account number: ");
            String accNo = scanner.nextLine();

            if (!accounts.containsKey(accNo)) {
                System.out.println(" Account not registered.");
                return;
            }

            Account acc = accounts.get(accNo);
            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();

            if (!acc.validatePin(pin)) {
                System.out.println(" Incorrect PIN.");
                return;
            }

            System.out.println(" Welcome, " + acc.getAccountHolderName() + "!");
            userMenu(acc);
        } catch (Exception e) {
            System.out.println(" Login error: " + e.getMessage());
        }
    }

    private static void userMenu(Account acc) {
        int choice=0;
        do {
            try {
                System.out.println("\n==== ACCOUNT MENU ====");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Check Balance");
                System.out.println("4. Transaction History");
                System.out.println("5. Account Details");
                System.out.println("6. Logout");
                System.out.print("Choose an option: ");
                choice = getIntInput();

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter amount to deposit: ₹");
                        acc.deposit(getDoubleInput());
                    }
                    case 2 -> {
                        System.out.print("Enter amount to withdraw: ₹");
                        acc.withdraw(getDoubleInput());
                    }
                    case 3 -> System.out.println(" Balance: ₹" + acc.getBalance());
                    case 4 -> acc.showTransactionHistory();
                    case 5 -> acc.showAccountDetails();
                    case 6 -> System.out.println(" Logged out.");
                    default -> System.out.println(" Invalid option.");
                }

                FileManager.saveAccounts(accounts); // Save after any change
            } catch (Exception e) {
                System.out.println(" Error: " + e.getMessage());
            }
        } while (choice != 6);
    }

    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print(" Enter a valid number: ");
            scanner.next();
        }
        int val = scanner.nextInt();
        scanner.nextLine();
        return val;
    }

    private static double getDoubleInput() {
        while (!scanner.hasNextDouble()) {
            System.out.print(" Enter a valid amount: ");
            scanner.next();
        }
        double val = scanner.nextDouble();
        scanner.nextLine();
        return val;
    }
}
