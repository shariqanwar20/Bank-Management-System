public class LinkedList <E extends Comparable <E>>
{
    Node<E> head;
    public void insert(E data)
    { //code here \
        Node newNode = new Node(data);
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
    public Node find(E d) {
        // find the node with value d
        Node<E> temp = head;

        while (temp != null) {
            if (d.compareTo(temp.data) == 0) {
                return temp;
            } else {
                temp = temp.next;
            }
        }
        return null;
    }
    public void remove(E d)
    { // find the node with value d and remove that node
        // first node to be removed
        if (d.compareTo(head.data) == 0)
        {
            head.next.prev = null;
            head = head.next;
        }
        else {
            Node curr = head;
            // Node prev = head;

            // middle or last
            while (curr != null) {

                if (d == curr.data) {
                    Node temp;
                    //temp = curr.prev.next;
                    // middle data
                    if (curr.next != null) {
                        curr.prev.next = curr.next;
                        curr.next.prev = curr.prev;
                        //curr.next.prev = temp;
                        break;
                    }
                    // last data
                    else
                    {
                        curr.prev.next = null;

                    }
                }
                //prev = curr;
                curr = curr.next;

            }
        }
    }
    public boolean isEmpty()
    { //return true if linked list is empty

        if (head == null)
            return true;
        else
            return false;
    }

    public int length() { // return the number of nodes in the list length is zero for the empty list
        int length = 0;

        Node temp = head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }
    public String toString() // print the list as comma separated values
    {
        String print = " ";
        Node temp = head;

        while (temp.next != null) {
            print = print + temp.data + ", ";
            temp = temp.next;
        }
        print += temp.data;

        return print;
    }


}
