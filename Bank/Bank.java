package Bank;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import Account.Account;
import Account.AccountNode;
import Structs.AccountType;
import Structs.TransactionType;
import Transaction.Transaction;

// Singleton class as there should be only one bank

public class Bank {
    private static Bank bank = null;
    private Transaction transactionTable;
    private Account accountTable;

    private int TRANSACTION_ID = 3583042;
    private int ACCOUNT_ID = 13276;

    private Bank() {
        this.loadAccountData();
        this.loadTransactionData();
    }

    public static Bank getBankInstance() {
        if (bank == null) {
            bank = new Bank();
        }
        return bank;
    }

    

    // private String generateTransaction(String transId, int transactionAmount, String accountId, float accountBalance) {
        

    //     return transId + "," + accountId + "," + transactionAmount + "," + accountBalance + "," + date + ","
    //             + timeStamp;
    // }

    // Admin Panel
    public void createAccount() {
        // create Account based on information from parameter like name, cnic etc
        // generate a csv line like in generateTransaction and append it to Accounts.csv
    }

    public void updateAccount() {
        // fetch the account using accountId, change the info by updating in accountTable and try to
        // append the file by either replacing the exact line or make a method that rewites the data in file
        // before exiting of code

    }

    public void deleteAccount() {
        // disable account
    }

    public void searchAccount() {

    }

    public void showAllAccounts() {
        // search account
    }

    // Staff Panel
    public void depositAmount(String accountNumber, int amount) {
        try {
            AccountNode acc = this.accountTable.search(accountNumber);
            System.out.println("Acccount => " + acc);

            int transIndex = acc.getTransactionIndexes().head.data;

            String[] metaData = new String[6];
            float accBalance = this.transactionTable.getTransAtSpecificIndex(transIndex).accountBalance;
            metaData[0] = "T0" + TRANSACTION_ID++;
            metaData[1] = accountNumber;
            metaData[2] = "" + amount;
            metaData[3] = "" + (amount + accBalance);
            metaData[4] = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
            metaData[5] = new SimpleDateFormat("HH:mm:ss").format(new Date());


            this.transactionTable.insert(new TransactionType(metaData), metaData[0]);

            String tx = metaData[0] + "," + metaData[1] + "," + metaData[2] + "," + metaData[3] + "," + metaData[4] + "," + metaData[5];

            File file = new File("./Data/Transactions.csv");
            FileWriter fr = new FileWriter(file, true);
            fr.write(tx);
            fr.close();
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public void withdrawAmount(String accountNumber, int amount) {
        try {
            AccountNode acc = this.accountTable.search(accountNumber);
            System.out.println("Acccount => " + acc);

            int transIndex = acc.getTransactionIndexes().head.data;
            String[] metaData = new String[6];
            float accBalance = this.transactionTable.getTransAtSpecificIndex(transIndex).accountBalance;
            
            if((accBalance - amount) < 0) {
                System.out.println("Error: the amount withdrawn is greater than account balance");
                return;
            }

            metaData[0] = "T0" + TRANSACTION_ID++;
            metaData[1] = accountNumber;
            metaData[2] = "" + amount;
            metaData[3] = "" + (accBalance - amount);
            metaData[4] = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
            metaData[5] = new SimpleDateFormat("HH:mm:ss").format(new Date());


            this.transactionTable.insert(new TransactionType(metaData), metaData[0]);

            String tx = metaData[0] + "," + metaData[1] + "," + metaData[2] + "," + metaData[3] + "," + metaData[4] + "," + metaData[5];


            File file = new File("./Data/Transactions.csv");
            FileWriter fr = new FileWriter(file, true);
            fr.write(tx);
            fr.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void displayTransactionLogsOfUser() {

    }

    private void loadTransactionData() {
        try {
            File file = new File("./Data/Transactions.csv");
            Scanner sc = new Scanner(file);

            BufferedReader reader = new BufferedReader(new FileReader("./Data/Transactions.csv"));
            int lines = 0;
            while (reader.readLine() != null)
                lines++;
            reader.close();

            transactionTable = new Transaction(lines);
            String[] data = sc.nextLine().split(",");
            while (sc.hasNext()) {
                data = sc.nextLine().split(",");
                TransactionType user = new TransactionType(data);
                transactionTable.insert(user, data[0]);
            }
            System.out.println("done" + transactionTable.numofCollisions + " " + transactionTable.numofOccupiedCells);
            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Input Output Exception");
        }
    }

    private void loadAccountData() {
        try {
            File file = new File("./Data/Accounts.csv");
            Scanner sc = new Scanner(file);

            BufferedReader reader = new BufferedReader(new FileReader("./Data/Accounts.csv"));
            int lines = 0;
            while (reader.readLine() != null)
                lines++;
            reader.close();

            accountTable = new Account(lines);
            String[] data = sc.nextLine().split(",");
            while (sc.hasNext()) {
                data = sc.nextLine().split(",");
                AccountType user = new AccountType(data);
                accountTable.insert(user, data[0]);
            }
            System.out.println("done" + accountTable.numofCollisions + " " + accountTable.numofOccupiedCells);
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Input Output Exception");
        }
    }
}
