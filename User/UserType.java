package User;

import Structs.Node;
import Structs.User;

public class UserType {
    User user;
    LinkedList next;
    Node<User> head;

    UserType(User user) {
        this.user = user;
    }

    public void insert(User data)
    {
        Node<User> newNode = new Node<User>(data);
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

    public Node<User> find(User d) {
        // find the node with value d
        Node<User> temp = head;

        while (temp != null) {
            if (temp.data.client_id.equals(d.client_id)) {
                return temp;
            } else {
                temp = temp.next;
            }
        }
        return null;
    }
    public void remove(User d)
    {
        if (head.data.client_id.equals(d.client_id))
        {
            head.next.previous = null;
            head = head.next;
        }
        else {
            Node<User> curr = head;
            while (curr != null) {

                if (curr.data.client_id.equals(d.client_id)) {
                    Node<User> temp;
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

        Node<User> temp = head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }
    public String toString()
    {
        String print = " ";
        Node<User> temp = head;

        while (temp.next != null) {
            print = print + temp.data + ", ";
            temp = temp.next;
        }
        print += temp.data;

        return print;
    }

}
