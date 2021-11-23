package Structs;

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

    public AccountType(String[] metadata) throws Exception {
        this.id = metadata[0];
        this.creationDate = new SimpleDateFormat("dd/MM/yyyy").parse(metadata[1]);
        this.cnic = metadata[2];
        this.name = metadata[3];
        this.phone = metadata[4];
        this.email = metadata[5];
        this.address = metadata[6];
        this.city = metadata[7];
        this.dateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse(metadata[8]);
    }

    public String toString() {
        String str = "{ AccountId: " + this.id + "\n" + "CreationDate: " + this.creationDate + "\n" + "Cnic: "
                + this.cnic + "\n" + "Name: " + this.name + "\n" + "DateOfBirth: " + this.dateOfBirth + "\n" + "Phone: "
                + this.phone + "\n" + "Email: " + this.email + "\n" + "Address: " + this.address + "\n" + "City: "
                + this.city + "\n";

        return str;
    }
}
