package OOP.ah21407.MP;

public class House_ah21407 extends House{
    private Room[] rooms;
    private final int NUM = 3;
    //testing 12345

    House_ah21407(Visitor V, Direction D){
        rooms = new Room[NUM];
        rooms[0] = new Room_ah21407();
        rooms[1] = new Room_ec21645();
        rooms[2] = new Room_ec211030();
    }

    House_ah21407() {
        rooms = new Room[NUM];
        rooms[0] = new Room_ah21407();
        rooms[1] = new Room_ec21645();
        rooms[2] = new Room_ec211030();
    }

    public Direction visit(Visitor V, Direction D){
        int index = 0;
        Room current = rooms[index];
        Direction direction = current.visit(V,Direction.TO_EAST);
        boolean leave = false;

        while(!leave){
            if(direction == Direction.TO_NORTH){
                V.tell("No room to north");
            }

            else if(direction == Direction.TO_SOUTH){
                V.tell("You left the house");
                leave = true;
            }

            else if(direction == Direction.TO_WEST){
                if(current == rooms[0]){
                    V.tell("You can't go west");
                }
                else{
                    index = index - 1;
                    current = rooms[index];
                }
            }

            else if(direction == Direction.TO_EAST){
                if(current == rooms[2]){
                    V.tell("You can't go east");
                }
                else{
                    index = index + 1;
                    current = rooms[index];
                }
            }

            direction = current.visit(V,direction);
        }
        return Direction.opposite(D);
    }
}
