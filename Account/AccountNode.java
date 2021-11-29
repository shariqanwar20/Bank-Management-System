package Account;

import Utils.LinkedList;
import Structs.AccountType;

public class AccountNode {
    LinkedList<Integer> transactionIndexes;
    AccountType data;

    AccountNode(AccountType data) {
        this.data = data;
        this.transactionIndexes = new LinkedList<Integer>();
    }

    public LinkedList<Integer> getTransactionIndexes() {
        return this.transactionIndexes;
    }
}

