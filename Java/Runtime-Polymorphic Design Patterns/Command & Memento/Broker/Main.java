
import java.util.ArrayList;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Broker b = new Broker("Fidelity");
        b.takeOver(Memento.add(new Buy("GOOG", 9, b)));
        b.takeOver(Memento.add(new Buy("AMZN", 1, b)));
        b.takeOver(Memento.add(new Sell("GOOG", 8, b)));
        b.placeOrder();

        Memento.undo();
        Memento.undo();

        Memento.redo();
    }
}

class Broker {

    private String name;
    private ArrayList<Order> order = new ArrayList<>();

    public Broker(String name) {
        this.name = name;
    }

    public void buy(String stock, int amount) {
        System.out.println("Stock [Ticker Symbol: " + stock + ", Quantity: " + amount + "] bought from " + name);
    }

    public void sell(String stock, int amount) {
        System.out.println("Stock [Ticker Symbol: " + stock + ", Quantity: " + amount + "] sold to " + name);
    }

    public void takeOver(Order t) {
        order.add(t);
    }

    public void placeOrder() {
        for (int i = 0; i < order.size(); i++) {
            order.get(i).execute();
        }
    }
}

interface Order {

    public void execute();

    public void undo();
}

class Buy implements Order {

    String name;
    int amount;
    Broker b;

    public Buy(String name, int amount, Broker b) {
        this.name = name;
        this.amount = amount;
        this.b = b;
    }

    @Override
    public void execute() {
        b.buy(name, amount);
    }

    @Override
    public void undo() {
        b.sell(name, amount);
    }

}

class Sell implements Order {

    String name;
    int amount;
    Broker b;

    public Sell(String name, int amount, Broker b) {
        this.name = name;
        this.amount = amount;
        this.b = b;
    }

    public void execute() {
        b.sell(name, amount);
    }

    public void undo() {
        b.buy(name, amount);
    }

}

class Memento {

    static Stack<Order> past = new Stack<>();
    static Stack<Order> future = new Stack<>();

    public static Order add(Order o) {
        past.push(o);
        return o;
    }

    public static void undo() {
        if (!past.empty()) {
            Order o = past.pop();
            future.push(o);
        }
    }

    public static void redo() {
        if (!future.empty()) {
            Order o = future.pop();
            o.execute();
            past.push(o);
        }
    }
}
