package Bank;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import Account.Account;
import Account.AccountNode;
import Structs.AccountType;
import Structs.Node;
import Structs.TransactionType;
import Transaction.Transaction;

// Singleton class as there should be only one bank

public class Bank {

    private static Bank bank = null;
    private Transaction transactionTable;
    private Account accountTable;

    private int TRANSACTION_ID = 3583042;
    private int ACCOUNT_ID = 13276;

    public Bank() {
        this.loadAccountData();
        this.loadTransactionData();
    }

    public Account getAccountTable() {
        return accountTable;
    }

    public Transaction getTransactionTable() {
        return this.transactionTable;
    }

    public static Bank getBankInstance() {
        if (bank == null) {
            bank = new Bank();
        }
        return bank;
    }

    // Admin Panel
    public void createAccount(String cnic, String name, String phone, String email, String address,
            String dateOfBirth) {
        // create Account based on information from parameter like name, cnic etc
        // generate a csv line like in generateTransaction and append it to Accounts.csv
        try {
            String[] metaData = new String[9];
            ACCOUNT_ID++;
            metaData[0] = "A000" + ACCOUNT_ID;
            metaData[1] = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
            metaData[2] = cnic;
            metaData[3] = name;
            metaData[4] = phone;
            metaData[5] = email;
            metaData[6] = address;
            metaData[7] = "Karachi";
            metaData[8] = dateOfBirth;

            String tx = metaData[0] + "," + metaData[1] + "," + metaData[2] + "," + metaData[3] + "," + metaData[4]
                    + "," + metaData[5] + "," + metaData[6] + "," + metaData[7] + "," + metaData[8];
            this.accountTable.insert(new AccountType(metaData), metaData[0]);
            File file = new File("./Data/Accounts.csv");
            FileWriter fr = new FileWriter(file, true);
            fr.write("\n" + tx);
            fr.close();
            loadAccountData();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void deleteAccount(String id) {
        // disable account
        accountTable.deactivate(id);
        loadAccountData();
    }

    public String searchAccount(String id) {
        return accountTable.search(id).getAccountData().toString();
    }

    public String showAllAccounts() {
        // search account
        return (accountTable.toString());
    }

    // Staff Panel
    public void depositAmount(String accountNumber, float amount) {
        try {
            AccountNode acc = this.accountTable.search(accountNumber);

            int transIndex = acc.getTransactionIndexes().head.data;

            String[] metaData = new String[6];
            float accBalance = this.transactionTable.getTransAtSpecificIndex(transIndex).accountBalance;

            System.out.println("Acccount => " + this.transactionTable.getTransAtSpecificIndex(transIndex));
            TRANSACTION_ID++;
            metaData[0] = "0" + TRANSACTION_ID;
            metaData[1] = accountNumber;
            metaData[2] = "" + amount;
            metaData[3] = "" + (amount + accBalance);
            metaData[4] = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
            metaData[5] = new SimpleDateFormat("HH:mm:ss").format(new Date());

            int index = this.transactionTable.insert(new TransactionType(metaData), metaData[0]);
            this.accountTable.insertTransIndex(index, acc);

            String tx = metaData[0] + "," + metaData[1] + "," + metaData[2] + "," + metaData[3] + "," + metaData[4]
                    + "," + metaData[5];

            File file = new File("./Data/Transactions.csv");
            FileWriter fr = new FileWriter(file, true);
            fr.write("\n" + tx);
            fr.close();
            loadTransactionData();
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public void withdrawAmount(String accountNumber, float amount) {
        try {
            AccountNode acc = this.accountTable.search(accountNumber);

            int transIndex = acc.getTransactionIndexes().head.data;

            String[] metaData = new String[6];
            float accBalance = this.transactionTable.getTransAtSpecificIndex(transIndex).accountBalance;

            System.out.println("Acccount => " + accBalance);
            System.out.println("After balacne => " + (accBalance - amount));
            if ((accBalance - amount) < 0) {
                System.out.println("Error: the amount withdrawn is greater than account balance");
                return;
            }
            TRANSACTION_ID++;
            metaData[0] = "0" + TRANSACTION_ID;
            metaData[1] = accountNumber;
            metaData[2] = "" + amount;
            metaData[3] = "" + (accBalance - amount);
            metaData[4] = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
            metaData[5] = new SimpleDateFormat("HH:mm:ss").format(new Date());

            this.transactionTable.insert(new TransactionType(metaData), metaData[0]);

            String tx = metaData[0] + "," + metaData[1] + "," + metaData[2] + "," + metaData[3] + "," + metaData[4]
                    + "," + metaData[5];

            File file = new File("./Data/Transactions.csv");
            FileWriter fr = new FileWriter(file, true);
            fr.write("\n" + tx);
            fr.close();
            loadTransactionData();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public String displayTransactionLogsOfUser(String id) {
        AccountNode acc = this.accountTable.search(id);
        Node<Integer> temp = acc.getTransactionIndexes().head;
        String str = "";
        TransactionType obj = null;
        while (temp != null) {
            obj = transactionTable.getTransAtSpecificIndex(temp.data);
            str += obj.toString();
            temp = temp.next;
        }
        return str;
    }

    private void loadTransactionData() {
        try {
            File file = new File("./Data/Transactions.csv");
            Scanner sc = new Scanner(file);

            BufferedReader reader = new BufferedReader(new FileReader("./Data/Transactions.csv"));
            long lines = 0;
            while (reader.readLine() != null) {
                lines++;
            }
            reader.close();

            transactionTable = new Transaction(lines);
            String[] data = sc.nextLine().split(",");
            while (sc.hasNext()) {
                data = sc.nextLine().split(",");
                TransactionType user = new TransactionType(data);
                AccountNode acc = this.accountTable.search(data[1]);
                int index = this.transactionTable.insert(user, data[0]);
                this.accountTable.insertTransIndex(index, acc);
            }
            System.out.println("done" + transactionTable.numofCollisions + " " + transactionTable.numofOccupiedCells);
            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("line 196 File not found");
        } catch (IOException e) {
            System.out.println("line 198 Input Output Exception");
        }
    }

    
    private void loadAccountData() {
        try {
            File file = new File("./Data/Accounts.csv");
            Scanner sc = new Scanner(file);

            BufferedReader reader = new BufferedReader(new FileReader("./Data/Accounts.csv"));
            int lines = 0;
            while (reader.readLine() != null) {
                lines++;
            }
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
