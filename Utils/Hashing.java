package Utils;

public class Hashing<E> {
    Object[] Table;
    int N = 0;
    int arrLength = 0;

    public int numofCollisions = 0;
    public int numofOccupiedCells = 0;
    int count = 0;

    public Hashing(long _N) {
        N = (int)_N;
        count = N / 3;
        // table size should be a prime number and 1/3 extra.
        int size = (int)(N + (N / 3));
        int newSize = getPrime(size);
        arrLength = newSize;
        Table = new Object[newSize]; 
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

    public void insert(E obj, String keyVal) {
        // keep maintain 1/3 empty cells
        if ((Table.length - numofOccupiedCells) > count) {
            // call Hash(key) and save return hash-value
            int insertionIndex = Hash(keyVal);
            /*
                if (no collision on hash-value) then place in table else place the collisions
                in a linked list
             */
            if (Table[insertionIndex] != null) {
                // while (n.next != null) {
                //     n = n.next;
                //     numofCollisions++;
                // }
                // n.next = your data
                numofCollisions++;
                
            }

            Table[insertionIndex] = obj;
            numofOccupiedCells++;
        }
    }

    // public boolean search(int key) {
    //     // search word in a hash table
    //     // call Hash(key) and save return hash-value
    //     int searchIndex = Hash(key);
    //     // if (value match at hash-value index) return true
    //     if (Table[searchIndex] == key) {
    //         return true;
    //     }
    //     // else call rehash function until empty cell found or it reaches threshold
    //     // limit of rehashes.
    //     else {
    //         int rehash = 0;
    //         while (Table[searchIndex] != 0 && (rehash < Table.length)) {
    //             if (Table[searchIndex] == key) {
    //                 return true;
    //             } else {
    //                 rehash++;
    //                 // searchIndex = LinearRehash(key, searchIndex);
    //                 searchIndex = QuadraticRehash(key, searchIndex);
    //             }

    //         }
    //     }
    //     return false;
    // }

    public String toString() {
        String str = "";
        for (int i = 0; i < Table.length; i++) {
            str = str + "[" + i + "] " + Table[i] + "\n";
        }
        return str;
    }

}
