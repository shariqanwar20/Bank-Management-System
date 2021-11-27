package AccountAndTransactions;

import Structs.Node;
import Structs.TransactionType;

public class AccountAndTransactionsLinkedList
{
    Node<TransactionType> head;
    public void insert(TransactionType data)
    {
        //Stack here since logs are always taken out from the recent ones

        Node<TransactionType> newNode = new Node<TransactionType>(data);
        if (head == null)
        {
            head = newNode;
        }
        else
        {
            head.previous = newNode;
            newNode.next = head;
            head = newNode;
        }
    }

    public void remove(TransactionType d)
    {
       d.status = false;
    }
    public boolean isEmpty()
    {

        if (head == null)
            return true;
        else
            return false;
    }

    public String toString()
    {
        String print = " ";
        Node<TransactionType> temp = head;

        while (temp.next != null) {
            print = temp.data +  print + ", ";
            temp = temp.next;
        }
        print += temp.data;

        return print;
    }
}
