package Structs;

public class Node<E> {
    public E data;
    public Node<E> next;
    public Node<E> previous;

    public Node(E d)
    {
        data = d;
        next = null;
        previous= null;
    }
}
