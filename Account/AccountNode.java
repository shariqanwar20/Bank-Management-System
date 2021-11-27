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
}

// public class AccountLinkedList {
//     AccountNode head;

//     public void insert(AccountType data) {
//         AccountNode newNode = new AccountNode(data);
//         if (head == null) {
//             head = newNode;
//         } else {
//             head.previous = newNode;
//             newNode.next = head;
//             head = newNode;
//         }
//     }

//     public AccountNode find(String id) {

//         // find the node with value d
//         AccountNode temp = head;

//         while (temp != null) {

//             if (temp.data.id.equals(id)) {
//                 return temp;
//             } else {
//                 temp = temp.next;
//             }
//         }
//         return null;
//     }

//     public void remove(AccountNode d) {
//         AccountNode curr = head;
//         while (curr != null) {

//             if (curr.data.id.equals(d.data.id)) {
//                 curr.data.status = false;
//                 return;
//             }
//             curr = curr.next;
//         }

//     }

//     public boolean isEmpty() {

//         if (head == null)
//             return true;
//         else
//             return false;
//     }

//     public int length() {
//         int length = 0;

//         AccountNode temp = head;
//         while (temp != null) {
//             length++;
//             temp = temp.next;
//         }
//         return length;
//     }

//     public String toString() {
//         String print = " ";
//         AccountNode temp = head;

//         while (temp.next != null) {
//             print = print + temp.data + ", ";
//             temp = temp.next;
//         }
//         print += temp.data;

//         return print;
//     }

//     public void displayRecord(AccountNode obj) {
//         System.out.println(obj.data.id + " " + obj.data.name + " " + obj.data.cnic + " " + obj.data.dateOfBirth + " "
//                 + obj.data.email + obj.data.city + " " + obj.data.address + " " + obj.data.phone + " "
//                 + obj.data.creationDate);
//     }

// }
