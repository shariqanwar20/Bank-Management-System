package Account;

import Structs.Node;
import Structs.AccountType;
import Structs.TransactionType;

public class AccountLinkedList {
    Node<AccountType> head;
    public void insert(AccountType data)
    { 
        Node<AccountType> newNode = new Node<AccountType>(data);
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

    public Node<AccountType> find(String id) {
        // find the node with value d
        Node<AccountType> temp = head;

        while (temp != null) {
            if (temp.data.id.equals(id)) {
                return temp;
            } else {
                temp = temp.next;
            }
        }
        return null;
    }
    public void remove(AccountType d)
    { 
        if (head.data.id.equals(d.id))
        {
            head.next.previous = null;
            head = head.next;
        }
        else {
            Node<AccountType> curr = head;
            while (curr != null) {

                if (curr.data.id.equals(d.id)) {
                    Node<AccountType> temp;
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

        Node<AccountType> temp = head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }
    public String toString() 
    {
        String print = " ";
        Node<AccountType> temp = head;

        while (temp.next != null) {
            print = print + temp.data + ", ";
            temp = temp.next;
        }
        print += temp.data;

        return print;
    }
    public void displayRecord(Node<AccountType> obj)
    {
        System.out.println(obj.data.id + " " + obj.data.name + " " + obj.data.cnic + " " + obj.data.dateOfBirth +  " "  + obj.data.email + obj.data.city + " " + obj.data.address + " " + obj.data.phone + " " + obj.data.creationDate);
    }

}
