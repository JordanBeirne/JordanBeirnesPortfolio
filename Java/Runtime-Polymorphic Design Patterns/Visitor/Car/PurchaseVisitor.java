// Jordan Beirne - CSC 402 - Dr. Kim - SA4 - 27 October 2025

public class PurchaseVisitor implements Visitor {

    private static PurchaseVisitor instance;

    private PurchaseVisitor() {

    }

    public static PurchaseVisitor getInstance() {
        if (instance == null) {
            instance = new PurchaseVisitor();
        }
        return instance;
    }

    public void visit(Wheel wheel) {
        System.out.println("The wheel was purchased in " + wheel.year);
    }

    public void visit(Engine engine) {
        System.out.println("The engine was purchased in " + engine.year);
    }

    public void visit(Body body) {
        System.out.println("The body part was purchased in " + body.year);
    }
}
