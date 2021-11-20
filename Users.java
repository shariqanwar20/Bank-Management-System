import java.io.*;
import java.util.Scanner;

/* import User Type */
import Structs.User;
import Utils.Hashing;

public class Users {
    public static void main(String[] args) throws Exception {

        try {
            File file = new File("./Data/completedclient.csv");
            Scanner sc = new Scanner(file);
            
            BufferedReader reader = new BufferedReader(new FileReader("./Data/completedclient.csv"));
            int lines = 0;
            while (reader.readLine() != null) lines++;
            reader.close();

           
            Hashing<User> hashTable = new Hashing<User>(lines);
            
            // String[] labels = sc.nextLine().split(",");
            // System.out.println(labels[4]);
            
            String[] data = sc.nextLine().split(",");
            while(sc.hasNext()) {
                data = sc.nextLine().split(",");
                User user = new User(data);
                hashTable.insert(user, data[0]);
            }
            System.out.println(data[0]);
            System.out.println("done" + hashTable.numofCollisions + " " + hashTable.numofOccupiedCells);
            sc.close();
            

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

    }
}