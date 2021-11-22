package User;

import Structs.User;

public class HashingUser {

    UserType[] Table;
    int count = 0;
    
    int N = 0;
    int arrLength = 0;

    public int numofCollisions = 0;
    public int numofOccupiedCells = 0;
    

    public HashingUser(long _N) {
        N = (int)_N;
        count = N / 3;
        // table size should be a prime number and 1/3 extra.
        int size = (int)(N + (N / 3));
        int newSize = getPrime(size);
        arrLength = newSize;
        Table = new UserType[newSize]; 
    }

    private int getPrime(int n) {
        while (true) {
            if (isPrime(n))
                return n;
            n++;
        }
    }

    private boolean isPrime(int n) {
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    public int Hash(String key) {
        // compute hash value by taking mod on key value and return remainder
        int hash = 0;
        String newKey = key;
        newKey.toLowerCase();
        for (int i=0, n=key.length(); i<n; i++)
        {
            hash = (hash << 7) ^ newKey.charAt(i);
        }
        System.out.println(hash % arrLength);
        return hash % arrLength;
    }

    public void insert(User obj, String keyVal) {
        UserType newUser = new UserType(obj);
        if ((Table.length - numofOccupiedCells) > count) {
            // call Hash(key) and save return hash-value
            int insertionIndex = Hash(keyVal);
            /*
                if (no collision on hash-value) then place in table else place the collisions
                in a linked list
             */
            if (Table[insertionIndex] != null) {
                Table[insertionIndex].next.insert(obj);
                numofCollisions++;
            }

            Table[insertionIndex] = newUser;
            numofOccupiedCells++;
        }
    }

    public boolean search() {
        //TODO
        return true;
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < Table.length; i++) {
            str = str + "[" + i + "] " + Table[i] + "\n";
        }
        return str;
    }
}
