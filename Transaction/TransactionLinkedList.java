package Transaction;

import Structs.Node;
import Structs.TransactionType;

public class TransactionLinkedList {
    Node<TransactionType> head;
    public void insert(TransactionType data)
    { 
        Node<TransactionType> newNode = new Node<TransactionType>(data);
        if (head == null) {
            head = newNode;
        }
        else
        {
            head.previous = newNode;
            newNode.next = head;
            head = newNode;
        }
    }


    public Node<TransactionType> find(String id) {

        // find the node with value d
        Node<TransactionType> temp = head;

        while (temp != null) {

            if (temp.data.id.equals(id)) {

                return temp;
            } else {
                temp = temp.next;
            }
        }
        return null;
    }
    public void remove(TransactionType d)
    { 
        if (head.data.id.equals(d.id))
        {
            head.next.previous = null;
            head = head.next;
        }
        else {
            Node<TransactionType> curr = head;
            while (curr != null) {

                if (curr.data.id.equals(d.id)) {
                    Node<TransactionType> temp;
                    //temp = curr.prev.next;

                    if (curr.next != null) {
                        temp = curr.previous;
                        curr.previous= curr.next;
                        curr.next.previous = temp;
                        break;
                    }
                    // last data
                    else
                    {
                        curr.previous.next = null;

                    }
                }
                curr = curr.next;

            }
        }
    }
    public boolean isEmpty()
    {

        if (head == null)
            return true;
        else
            return false;
    }

    public int length() {
        int length = 0;

        Node<TransactionType> temp = head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }
    public String toString() 
    {
        String print = " ";
        Node<TransactionType> temp = head;

        while (temp.next != null) {
            print = print + temp.data + ", ";
            temp = temp.next;
        }
        print += temp.data;

        return print;
    }

    public void displayRecord(Node<TransactionType> obj)
    {
        System.out.println(obj.data.id + " " + obj.data.accountId + " " + obj.data.accountBalance + " " + obj.data.date + " " + obj.data.time + " " + obj.data.transactionAmount);

    }

}
