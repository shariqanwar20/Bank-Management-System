package Utils;

import Structs.Node;

public class LinkedList<T extends Comparable<T>> {
    public Node<T> head;

    public void insert(T data) {
        Node<T> newNode = new Node<T>(data);
        if (head == null) {
            head = newNode;
        } else {
            head.previous = newNode;
            newNode.next = head;
            head = newNode;
        }
    }

    public Node<T> find(T id) {

        // find the node with value d
        Node<T> temp = head;

        while (temp != null) {

            if (temp.data.equals(id)) {
                return temp;
            } else {
                temp = temp.next;
            }
        }
        return null;
    }

    public boolean isEmpty() {

        if (head == null)
            return true;
        else
            return false;
    }

    public int length() {
        int length = 0;

        Node<T> temp = head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    public String toString() {
        String print = " ";
        Node<T> temp = head;

        while (temp.next != null) {
            print = print + temp.data + ", ";
            temp = temp.next;
        }
        print += temp.data;

        return print;
    }

}
