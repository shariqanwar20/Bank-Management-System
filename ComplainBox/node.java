package ComplainBox;

public class node
{
    String Complain;
    String Date;
    int id;
    node next;

    public node (String Complain, String Date, int id)
    {
       this.Complain = Complain;
       this.Date = Date;
       this.id = id;
    }
}
