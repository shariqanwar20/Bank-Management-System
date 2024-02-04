package Transaction;

// import java.security.MessageDigest;
// import java.math.BigInteger;
import Structs.TransactionType;

public class Transaction {

    TransactionType[] Table;
    int count = 0;

    int N = 0;
    int arrLength = 0;

    public int numofCollisions = 0;
    public int numofOccupiedCells = 0;

    public Transaction(long _N) {
        N = (int) _N;
        count = N / 3;
        // table size should be a prime number and 1/3 extra.
        int size = (int) (N + (8*N / 9));
        int newSize = getPrime(size);
        arrLength = newSize;
        System.out.println("size" + arrLength);
        Table = new TransactionType[arrLength];
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

    public TransactionType getTransAtSpecificIndex(int index) {
        return this.Table[index];
    }

    public int Hash(String key) {
        // compute hash value by taking mod on key value and return remainder
        int hash = 0;
        int newKey = Integer.parseInt(key.substring(4));
        hash = (13 * newKey) + (7 * (int) Math.pow(newKey + Math.random() * 10, 2)) + (int)(Math.random() * 11);

        return Math.abs(hash % arrLength);
    }

    public int Hash(String key, int _i) {
        // compute hash value by taking mod on key value and return remainder
        int hash = 0;
        int newKey = Integer.parseInt(key.substring(4));
        hash = (13 * newKey) + (7 * (int) Math.pow(newKey + Math.random() * 10, 2)) + (int)(Math.random() * 11);

        return Math.abs(hash % arrLength);
    }

    public int insert(TransactionType obj, String keyVal) {
        int rehash = 1;
        if ((Table.length - numofOccupiedCells) > count) {
            // call Hash(key) and save return hash-value
            int insertionIndex = Hash(keyVal);
            if (Table[insertionIndex] != null) {
                if (rehash < Table.length) {
                    while (Table[insertionIndex] != null) {
                        insertionIndex = Hash(keyVal, rehash);
                        // also count number of collisions on each key insertion
                        numofCollisions++;
                        rehash++;
                    }

                } else {
                    return -1;
                }
            }

            /* first instantiate a linked list at this position then insert data */
            Table[insertionIndex] = obj;
            numofOccupiedCells++;
            return insertionIndex;
        }
        return -1;
    }

    public TransactionType search(String id) {
        int insertionIndex = Hash(id);
        int rehash = 1;

        if (Table[insertionIndex] != null) {
            while (Table[insertionIndex] != null && insertionIndex < Table.length
                    && !Table[insertionIndex].id.equals(id)) {
                insertionIndex = Hash(id, rehash);
                rehash++;
            }

            if (Table[insertionIndex].id.equals(id)) {
                return Table[insertionIndex];
            }
        }

        return null;

    }

    public String toString() {
        String str = "";
        for (int i = 0; i < Table.length; i++) {
            str = str + "[" + i + "] " + Table[i] + "\n";
        }
        return str;
    }
}
