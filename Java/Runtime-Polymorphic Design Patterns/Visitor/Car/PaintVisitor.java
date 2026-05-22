// Jordan Beirne - CSC 402 - Dr. Kim - SA4 - 27 October 2025

public class PaintVisitor implements Visitor {

    private static PaintVisitor instance;

    private PaintVisitor() {

    }

    public static PaintVisitor getInstance() {
        if (instance == null) {
            instance = new PaintVisitor();
        }
        return instance;
    }

    public void visit(Wheel wheel) {
        System.out.println("The wheel was painted in " + wheel.color);
    }

    public void visit(Engine engine) {
        System.out.println("The engine was painted in " + engine.color);
    }

    public void visit(Body body) {
        System.out.println("The body part was painted in " + body.color);
    }
}
