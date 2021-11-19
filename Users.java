import java.io.*;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

class User {
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

    User(String[] metadata) throws Exception {
                this.client_id = metadata[0];
                this.sex = metadata[1];
                this.fulldate = new SimpleDateFormat("dd/MM/yyyy").parse(metadata[2]);
                this.day = Integer.parseInt(metadata[3]);
                this.month =Integer.parseInt(metadata[4]);
                this.year = Integer.parseInt(metadata[5]);
                this.age = Integer.parseInt(metadata[6]);
                this.cnic =metadata[7];
                this.first =metadata[8];
                this.middle = metadata[9];
                this.last =metadata[10];
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

public class Users {
    public static void main(String[] args) throws Exception {

        try {
            File file = new File("completedclient.csv");
            Scanner sc = new Scanner(file);
            ArrayList<User> users = new ArrayList<User>();
            
            String[] labels = sc.nextLine().split(",");
            System.out.println(labels[4]);
            
            String[] data;
            while(sc.hasNext()) {
                data = sc.nextLine().split(",");
                User user = new User(data);
            }
            

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

    }
}