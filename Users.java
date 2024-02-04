import java.io.*;
import java.util.Scanner;

import Account.Account;
/* import User Type */
import Bank.Bank;


public class Users {
    public static void main(String[] args) throws Exception {

        //get Bank Instance
        Bank bank = Bank.getBankInstance();
        
        // bank.createAccount("42101", "Shariq", "+92", "shrek69@gmail.com", "R-", "24/12/2001");
        System.out.println(bank.searchAccount("A00000906"));
        bank.deleteAccount("A00000906");
        System.out.println(bank.searchAccount("A00000906"));

        // bank.depositAmount("A00004355", 420);

        bank.withdrawAmount("A00004355", 200);

        // System.out.println(bank.displayTransactionLogsOfUser("A00004355"));
    }
}