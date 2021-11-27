public class ListQueue <T>
{
    ListQueue obj = new ListQueue();
    node<T> Front;
    node<T> Rear;

    public void Enqueue(String Complain, String Date, int ID)
    {
        node <T> n = new node<T>(Complain, Date, ID);

        if (Front == null && Rear == null)
        {
            Front = n;
        }

        else
        {
            Rear.next = n;
        }
        Rear = n;
    }

    public void Dequeue()
    {
        obj.Enqueue(Front.Complain, Front.Date, Front.id);

        if (isEmpty())
        {
            return;
        }
        else
            {
                Front = Front.next;
        }
    }

    public boolean isEmpty()
    {
        if (Front == null)
        {
            return true;
        }

        else
            return false;
    }
}
