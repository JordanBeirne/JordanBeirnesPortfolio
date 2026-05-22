// Jordan Beirne - CSC 402 - Dr. Kim - SA4 - 27 October 2025

class RepairVisitor implements Visitor {

    private static RepairVisitor instance;

    private RepairVisitor() {

    }

    public static RepairVisitor getInstance() {
        if (instance == null) {
            instance = new RepairVisitor();
        }
        return instance;
    }

    @Override
    public void visit(Wheel wheel) {
        System.out.println("The wheel needs repairs since it cannot " + wheel.role);
    }

    public void visit(Engine engine) {
        System.out.println("The engine needs repairs since it cannot " + engine.role);
    }

    public void visit(Body body) {
        System.out.println("The body part needs repairs since it cannot " + body.role);
    }
}
