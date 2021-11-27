public class node<T>
{
    String Complain;
    String Date;
    int id;
    node<T> next;

    public node (String Complain, String Date, int id)
    {
       this.Complain = Complain;
       this.Date = Date;
       this.id = id;
    }
}
