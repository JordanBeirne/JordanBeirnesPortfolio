
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Order o = new Order(20);
        o.addItem(new Item("shoes"));
        o.addItem(new Item("coat"));
        o.addItem(new Item("phone charger"));

        for (Notification n : o.notifPref) {
            n.notifyCustomer(o);
        }

    }
}

class Order {

    int number;
    ArrayList<Item> contents;
    ArrayList<Notification> notifPref;

    public Order(int number) {
        this.number = number;
        this.notifPref = new ArrayList<>();
        notifPref.add(new SMS());
        notifPref.add(new Email());
        notifPref.add(new AppNotification());
        contents = new ArrayList<>();
    }

    public void addItem(Item x) {
        contents.add(x);
    }
}

class Item {

    String name;

    public Item(String name) {
        this.name = name;
    }
}

interface Notification {

    public void notifyCustomer(Order o);
}

class SMS implements Notification {

    public void notifyCustomer(Order o) {
        System.out.println("TEXT ALERT: Order " + o.number + " shipped!");
    }
}

class Email implements Notification {

    public void notifyCustomer(Order o) {
        System.out.println("Email: Order " + o.number + " shipped!");
    }
}

class AppNotification implements Notification {

    public void notifyCustomer(Order o) {
        System.out.println("Amazon App: Order " + o.number + " shipped!");
    }
}
