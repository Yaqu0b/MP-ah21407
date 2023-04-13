package OOP.ah21407.MP;

interface Visitable {

    Direction visit( // Returns direction the visitor leaves towards.
                     Visitor visitor,
                     Direction directionVistorArrivesFrom);
}