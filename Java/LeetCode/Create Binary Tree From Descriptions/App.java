import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class App {

    /*
        LeetCode 2196

        Constructs a binary tree given a list of descriptions where each
        description specifies a parent node, child node, and whether the
        child belongs on the left or right side.

        Uses a map to cache and retrieve Node objects by value so each
        Node is created only once. Tracks all child Nodes in a list,
        then identifies the root as the only Node that never appears
        as a child.
    */

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {

        int[][] descriptions1 = {
                {20,15,1},
                {20,17,0},
                {50,20,1},
                {50,80,0},
                {80,19,1}
        };

        int[][] descriptions2 = {
            {1,2,1},
            {2,3,0},
            {3,4,1}
        };

        Node root1 = createBinaryTree(descriptions1);
        Node root2 = createBinaryTree(descriptions2);

        print(root1);
        System.out.println("---");
        print(root2);
        System.out.println("---");
    }

    public Node createBinaryTree(int[][] descriptions) {

        Map<Integer, Node> nodes = new HashMap<>();
        ArrayList<Integer> children = new ArrayList<>();

        for (int[] description : descriptions) {

            int parentValue = description[0];
            int childValue = description[1];
            int isLeft = description[2];

            nodes.putIfAbsent(parentValue, new Node(parentValue));

            nodes.putIfAbsent(childValue, new Node(childValue));

            Node parent = nodes.get(parentValue);
            Node child = nodes.get(childValue);

            if (isLeft == 1) {
                parent.setLeft(child);
            }
            else {
                parent.setRight(child);
            }

            children.add(childValue);
        }

        for (int value : nodes.keySet()) {
            if (!children.contains(value)) {
                return nodes.get(value);
            }
        }

        return null;
    }

    public void print(Node root) {

        Queue<Node> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {

            Node current = queue.poll();

            if (current == null) {
                System.out.print("null ");
                continue;
            }

            System.out.print(current.getData() + " ");

            queue.add(current.getLeft());
            queue.add(current.getRight());
        }

        System.out.println();
    }
}

class Node {

    private int data;
    private Node left;
    private Node right;

    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return this.data;
    }

    public Node getLeft() {
        return this.left;
    }

    public Node getRight() {
        return this.right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}