
public class Engine implements CarElement {

    public int year = 2017;
    public String role = "convert gasoline into motion";
    public String color = "black";

    public void accept(Visitor v) {
        v.visit(this);
    }
}
