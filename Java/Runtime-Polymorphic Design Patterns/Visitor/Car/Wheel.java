
public class Wheel implements CarElement {

    public int year = 2011;
    public String role = "support tires";
    public String color = "red";

    public void accept(Visitor v) {
        v.visit(this);
    }
}
