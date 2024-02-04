package Structs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionType {
    public String id;
    public String accountId;
    public float transactionAmount;
    public float accountBalance;
    public Date date;
    public Date time;

    public TransactionType(String[] metadata) {
        try {
            this.id = metadata[0];
            this.accountId = metadata[1];
            this.transactionAmount = Float.parseFloat(metadata[2]);
            this.accountBalance = Float.parseFloat(metadata[3]);
            this.date = new SimpleDateFormat("dd/MM/yyyy").parse(metadata[4]);
            // this.time = new SimpleDateFormat("HH:mm:ss").parse(metadata[5]);
        } catch (ParseException e) {
            System.out.println(e);
        }

    }

    public String toString() {
        String str = "{ TransactionId: " + this.id + "\n" + "AccountId: " + this.accountId + "\n" + "AccountBalance: "
                + this.accountBalance + "\n" + "Date: " + this.date + "\n" + "TimeStamp: " + this.time + "\n";

        return str;
    }
}
