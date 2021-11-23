import java.io.*;
import java.util.Scanner;

import Account.Account;
/* import User Type */
import Structs.AccountType;
import Structs.TransactionType;
import Transaction.Transaction;

public class Users {
    public static void main(String[] args) throws Exception {

        try {
            File file = new File("./Data/Transactions.csv");
            Scanner sc = new Scanner(file);
            
            BufferedReader reader = new BufferedReader(new FileReader("./Data/Transactions.csv"));
            int lines = 0;
            while (reader.readLine() != null) lines++;
            reader.close();

           
            Transaction hashTable = new Transaction(lines);
            
            // String[] labels = sc.nextLine().split(",");
            // System.out.println(labels[4]);
            
            String[] data = sc.nextLine().split(",");
            while(sc.hasNext()) {
                data = sc.nextLine().split(",");
                TransactionType user = new TransactionType(data);
                hashTable.insert(user, data[0]);
            }
            System.out.println("done" + hashTable.numofCollisions + " " + hashTable.numofOccupiedCells);
            sc.close();
            

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

    }
}