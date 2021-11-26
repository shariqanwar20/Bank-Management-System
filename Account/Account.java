package Account;

import Structs.AccountType;

public class Account {

    AccountLinkedList[] Table;
    int count = 0;
    
    int N = 0;
    int arrLength = 0;

    public int numofCollisions = 0;
    public int numofOccupiedCells = 0;
    

    public Account(long _N) {
        N = (int)_N;
        count = N / 3;
        // table size should be a prime number and 1/3 extra.
        int size = (int)(N + (N / 3));
        int newSize = getPrime(size);
        arrLength = newSize;
        Table = new AccountLinkedList[newSize]; 
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
        return hash % arrLength;
    }

    public void insert(AccountType obj, String keyVal) {
        if ((Table.length - numofOccupiedCells) > count) {
            // call Hash(key) and save return hash-value
            int insertionIndex = Hash(keyVal);
            /*
             place the data in an already instatntiated linked list
             */
            if (Table[insertionIndex] != null) {
                Table[insertionIndex].insert(obj);
                numofCollisions++;
            }

            /* first instantiate a linked list at this position then insert data */
            Table[insertionIndex] = new AccountLinkedList();
            Table[insertionIndex].insert(obj);
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
