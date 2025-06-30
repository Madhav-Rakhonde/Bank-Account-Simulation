import java.io.*;
import java.util.*;

public class FileManager {
    private static final String FILE_NAME = "accounts.txt";

    public static Map<String, Account> loadAccounts() {
        Map<String, Account> accounts = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            List<String> buffer = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                if (line.equals("BEGIN_ACCOUNT")) buffer.clear();
                buffer.add(line);
                if (line.equals("END_ACCOUNT")) {
                    Account acc = Account.fromFileLines(buffer);
                    accounts.put(acc.getAccountNumber(), acc);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(" No account file found, starting fresh.");
        } catch (IOException | NumberFormatException e) {
            System.out.println(" Error loading accounts: " + e.getMessage());
        }

        return accounts;
    }

    public static void saveAccounts(Map<String, Account> accounts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Account acc : accounts.values()) {
                for (String line : acc.toFileString()) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println(" Error saving accounts: " + e.getMessage());
        }
    }
}

