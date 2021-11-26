package AccountAndTransactions;

import Account.AccountLinkedList;
import Structs.AccountType;
import Structs.Node;
import Structs.TransactionType;

public class AccountAndTransactions
{
    AccountAndTransactionsLinkedList[] Table;
    int count = 0;

    int N = 0;
    int arrLength = 0;

    public int numofCollisions = 0;
    public int numofOccupiedCells = 0;


    public AccountAndTransactions(long _N) {
        N = (int)_N;
        count = N / 3;
        // table size should be a prime number and 1/3 extra.
        int size = (int)(N + (N / 3));
        int newSize = getPrime(size);
        arrLength = newSize;
        Table = new AccountAndTransactionsLinkedList[newSize];
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

    public int RehashLinear(int key, int i)
    {
        //first test linear probing, then test Quadratic probing and compare both technique on same data
        //with respect to number of collision
        return (key + i) % Table.length;
    }

   public void insert(TransactionType obj, String keyVal) {
        int Rehash = 0;
        if ((Table.length - numofOccupiedCells) > count) {
            // call Hash(key) and save return hash-value
            int insertionIndex = Hash(keyVal);
            /*
             place the data in an already instatntiated linked list
             */
            /*if (Table[insertionIndex] != null) {
                Table[insertionIndex].insert(obj);
                numofCollisions++;
            } */
           //Getting another value through rehash

            // if (no collision on hash-value) then place in table
            if(Table[insertionIndex] != null)
            {
                if(Rehash < Table.length)
                {
                    while (Table[insertionIndex] != null)
                    {
                        insertionIndex = RehashLinear(keyVal, insertionIndex);
                        // also count number of collisions on each key insertion
                        numofCollisions++;
                    }
                    Rehash++;
                }
                else
                {
                    return;
                }
            }
            Table[insertionIndex].insert(obj);
            numofOccupiedCells++;

        }
    }
    public boolean search(String AccountNumb) {

        boolean status = true;
        int Rehash = 0;

        int hashValue = Hash(AccountNumb);

        if(Table[hashValue] != null)
        {
            while (Table[hashValue] != null && Rehash < Table.length && Table[hashValue].head.data.accountId != AccountNumb)
            {
                hashValue = RehashLinear(hashValue, hashValue);

                // also count number of collisions on each key insertion
                Rehash++;
            }

            if(Rehash >= Table.length)
            {
                status = false;
            }

            else if (Table[hashValue] == null)
            {
                status = false;
            }

            else if ( Table[hashValue].head.data.accountId.equals(hashValue))
            {
                status = true;
            }
        }
        return status;
    }


    public String toString() {
        String str = "";
        for (int i = 0; i < Table.length; i++) {
            str = str + "[" + i + "] " + Table[i] + "\n";
        }
        return str;
    }
}
