//Q2.java

public class Main {

    public static void main(String[] args) {
        // Represents: (3 + 5) * 2
        Expression expr = new Multiplication(
                new Addition(new Number(3), new Number(5)),
                new Number(2)
        );

        // Evaluate
        int result = expr.accept(Visitor.getInstance());
        System.out.println("Evaluated Result: " + result); // Output: 16

        // Print
        String str = expr.print();
        System.out.println("Printed Expression: " + str); // Output: ((3 + 5) * 2)
    }
}

//Expression.java
interface Expression {

    int accept(Visitor v);

    String print();
}

//Multiplication.java
class Multiplication implements Expression {

    private final Expression left, right;

    public Multiplication(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int accept(Visitor v) {
        return v.visit(this);
    }

    @Override
    public String print() {
        return "(" + getLeft().print() + " * " + getRight().print() + ")";
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }
}

//Addition.java
class Addition implements Expression {

    private final Expression left, right;

    public Addition(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int accept(Visitor v) {
        return getLeft().accept(v) + getRight().accept(v);
    }

    @Override
    public String print() {
        return "(" + getLeft().print() + " + " + getRight().print() + ")";
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }
}

//Number.java
class Number implements Expression {

    private final int value;

    public Number(int value) {
        this.value = value;
    }

    @Override
    public int accept(Visitor v) {
        return v.visit(this);
    }

    @Override
    public String print() {
        return String.valueOf(getValue());
    }

    public int getValue() {
        return value;
    }
}

class Visitor {

    private static Visitor instance;

    private Visitor() {

    }

    public static Visitor getInstance() {
        if (instance == null) {
            return new Visitor();
        } else {
            return instance;
        }

    }

    public int visit(Addition a) {
        return a.getLeft().accept(this) + a.getRight().accept(this);
    }

    public int visit(Multiplication m) {
        return m.getLeft().accept(this) * m.getRight().accept(this);
    }

    public int visit(Number n) {
        return n.getValue();
    }
}
