package Structs;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    String client_id;
    String sex;
    Date fulldate;
    int day;
    int month;
    int year;
    int age;
    String cnic;
    String first;
    String middle;
    String last;
    String phone;
    String email;
    String address_1;
    String address_2;
    String city;
    String state;
    int zipcode;
    int district_id;

    public User(String[] metadata) throws Exception {
        this.client_id = metadata[0];
        this.sex = metadata[1];
        this.fulldate = new SimpleDateFormat("dd/MM/yyyy").parse(metadata[2]);
        this.day = Integer.parseInt(metadata[3]);
        this.month = Integer.parseInt(metadata[4]);
        this.year = Integer.parseInt(metadata[5]);
        this.age = Integer.parseInt(metadata[6]);
        this.cnic = metadata[7];
        this.first = metadata[8];
        this.middle = metadata[9];
        this.last = metadata[10];
        this.phone = metadata[11];
        this.email = metadata[12];
        this.address_1 = metadata[13];
        this.address_2 = metadata[14];
        this.city = metadata[15];
        this.state = metadata[16];
        this.zipcode = Integer.parseInt(metadata[17]);
        this.district_id = Integer.parseInt(metadata[18]);
    }
}
