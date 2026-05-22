// Jordan Beirne - CSC 402 - Dr. Kim - SA6

public class Main {

    public static void main(String[] args) {

        Subject subject = new SubjectForOnlyNewState();

        DecimalObserver decimal = new DecimalObserver(subject);
        BinaryObserver binary = new BinaryObserver(subject);
        HexaObserver hexa = new HexaObserver(subject);

        System.out.println("----- Setting state to 10 -----");
        decimal.setState(10);

        System.out.println("\n----- Setting state to 10 again (should not notify) -----");
        decimal.setState(10);

        System.out.println("\n----- Setting state to 15 -----");
        binary.setState(15);

        System.out.println("\n----- Removing Binary Observer -----");
        subject.detatch(binary);

        System.out.println("\n----- Setting state to 20 -----");
        hexa.setState(20);

        System.out.println("\n----- Testing contains() -----");
        System.out.println("Contains decimal: " + subjectContains(subject, decimal));
        System.out.println("Contains binary: " + subjectContains(subject, binary));
    }

    public static boolean subjectContains(Subject subject, Observer observer) {
        return true;
    }
}

class ObserverStack {

    int size;
    int capacity;
    Observer[] list;

    public ObserverStack(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.list = new Observer[capacity];
    }

    public void push(Observer o) {
        if (size <= capacity) {
            list[size++] = o;
        }
    }

    public Observer pop() {
        return list[--size];
    }

    public Observer peek() {
        return list[size];
    }

    public void remove(Observer o) {

        int index = -1;

        for (int i = 0; i < size; i++) {
            if (list[i] == o) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            return;
        }

        for (int i = index; i < size - 1; i++) {
            list[i] = list[i + 1];
        }

        list[size - 1] = null;
        size--;
    }

    public boolean contains(Observer o) {
        Observer[] temp = list;
        int tempSize = size;
        for (int i = 0; i < size; i++) {
            if (temp[i] == o) {
                return true;
            }
        }
        return false;
    }
}

class Subject {

    private ObserverStack observers = new ObserverStack(255);
    protected int state;

    public void attach(Observer o) {
        if (!observers.contains(o)) {
            this.observers.push(o);
        }
    }

    public void detatch(Observer o) {
        this.observers.remove(o);
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    private void notifyAllObservers() {
        for (int i = 0; i < observers.size; i++) {
            observers.list[i].update(state);
        }
    }
}

class Observer {

    private Subject subject = null;

    public Observer(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    public void update(int state) {
        System.out.println(state);
    }

    public void setState(int state) {
        this.subject.setState(state);
    }
}

class DecimalObserver extends Observer {

    public DecimalObserver(Subject subject) {
        super(subject);
    }

    public void update(int state) {
        System.out.println("Decimal State: " + state);
    }
}

class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject) {
        super(subject);
    }

    public void update(int state) {
        System.out.println("Binary State: " + Integer.toBinaryString(state));
    }
}

class HexaObserver extends Observer {

    Subject subject = null;

    public HexaObserver(Subject subject) {
        super(subject);
    }

    public void update(int state) {
        System.out.println("Hexa State: " + Integer.toHexString(state));
    }
}

class SubjectForOnlyNewState extends Subject {

    public void setState(int state) {
        if (super.state != state) {
            super.setState(state);
        }

    }
}
