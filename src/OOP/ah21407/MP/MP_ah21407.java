package OOP.ah21407.MP;

public class MP_ah21407
{
    public static void main(String[] args)
    {
        GUIVisitor_ah21407 visitor = new GUIVisitor_ah21407();
        House_ah21407 house = new House_ah21407();
        house.visit(visitor, Direction.FROM_NORTH);

        /*Visitable r = new Room_ah21407();
        Visitor v = new IOVisitor(System.out,System.in);
        r.visit(v,Direction.FROM_SOUTH);*/
    }
}