package Structs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AccountType {
    public String id;
    public Date creationDate;
    public String cnic;
    public String name;
    public String phone;
    public String email;
    public String address;
    public String city;
    public Date dateOfBirth;
    public Boolean status;

    public AccountType(String[] metadata) {
        try {
            this.id = metadata[0];
            this.creationDate = new SimpleDateFormat("dd/MM/yyyy").parse(metadata[1]);
            this.cnic = metadata[2];
            this.name = metadata[3];
            this.phone = metadata[4];
            this.email = metadata[5];
            this.address = metadata[6];
            this.city = metadata[7];
            this.dateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse(metadata[8]);
            this.status = true;

        } catch (ParseException e) {
            System.out.println(e);
        }
    }

    public String toString() {
        String str = "{ AccountId: " + this.id + "\n" + "CreationDate: " + this.creationDate + "\n" + "Cnic: "
                + this.cnic + "\n" + "Name: " + this.name + "\n" + "DateOfBirth: " + this.dateOfBirth + "\n" + "Phone: "
                + this.phone + "\n" + "Email: " + this.email + "\n" + "Address: " + this.address + "\n" + "City: "
                + this.city + "\n";

        return str;
    }
}
