package Account;
import Structs.AccountType;

public class Account {

    AccountNode[] Table;
    int count = 0;

    int N = 0;
    int arrLength = 0;

    public int numofCollisions = 0;
    public int numofOccupiedCells = 0;

    public Account(long _N) {
        N = (int) _N;
        count = N / 3;
        // table size should be a prime number and 1/3 extra.
        int size = (int) (N + (N / 3));
        int newSize = getPrime(size);
        arrLength = newSize;
        Table = new AccountNode[newSize];
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
        int newKey = Integer.parseInt(key.substring(4));
        hash = (1571 * newKey) + (arrLength/2);

        return hash % arrLength;
    }

    public int Hash(String key, int _i) {
        // compute hash value by taking mod on key value and return remainder
        int hash = 0;
        int newKey = Integer.parseInt(key.substring(4));
        hash = (1571 * newKey) + (arrLength/2);

        return (hash + _i) % arrLength;
    }

    public void insert(AccountType obj, String keyVal) {
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
                    return;
                }
            }

            /* first instantiate a linked list at this position then insert data */
            Table[insertionIndex] = new AccountNode(obj);
            numofOccupiedCells++;
        }
    }

    public void insertTransIndex(Integer index, AccountNode account) {
        account.transactionIndexes.insert(index);
    }

    public void deactivate(String id) {
        AccountNode account = this.search(id);
        account.data.status = false;
    }

    public AccountNode search(String id) {
        int insertionIndex = Hash(id);
        int rehash = 1;
        
        if(Table[insertionIndex] != null) {
            while(Table[insertionIndex] != null && insertionIndex< Table.length && !Table[insertionIndex].data.id.equals(id)) {
                insertionIndex = Hash(id, rehash);
                rehash++;
            }

            if(Table[insertionIndex].data.id.equals(id)) {
                return Table[insertionIndex];
            }
        }

        return null;

    }

    public AccountNode[] getAccountTable() {
        return this.Table;
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < Table.length; i++) {
            str = str + "[" + i + "] " + (Table[i] != null? Table[i].data.toString() : "Empty") + "\n";
        }
        return str;
    }
}
