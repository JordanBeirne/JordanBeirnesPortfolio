// Author: Jordan Beirne

import java.util.*;

/*
INPUT EXAMPLE 1 (POSSIBLE - simple pair split)
4
2
0 1
2 3

INPUT EXAMPLE 2 (NOT POSSIBLE - triangle cycle)
3
3
0 1
1 2
2 0

INPUT EXAMPLE 3 (POSSIBLE - disconnected components)
6
3
0 1
2 3
4 5

INPUT EXAMPLE 4 (POSSIBLE - chain graph)
5
4
0 1
1 2
2 3
3 4

INPUT EXAMPLE 5 (NOT POSSIBLE - odd cycle in larger graph)
5
5
0 1
1 2
2 3
3 4
4 0
 */
class Node {

    int ID;
    int section;
    ArrayList<Node> connections;
    boolean visited;

    public Node(int ID) {
        this.ID = ID;
        this.connections = new ArrayList<>();
        this.visited = false;
        this.section = 1;
    }

    public void addConnection(Node connection) {
        this.connections.add(connection);
    }
}

public class SeatingArrangement {

    static boolean seatingPossible = true;
    static int numAttendants;
    static int numRivals;
    static Node[] graph;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        constructGraph(s);

        for (int i = 0; i < numAttendants; i++) {
            if (!graph[i].visited) {
                DFS(graph[i], 1);
            }
        }

        if (seatingPossible) {
            System.out.println("SEATING POSSIBLE");

            System.out.println("Section 1:");
            for (Node n : graph) {
                if (n.section == 1) {
                    System.out.print(n.ID + " ");
                }
            }

            System.out.println("\nSection 2:");
            for (Node n : graph) {
                if (n.section == 2) {
                    System.out.print(n.ID + " ");
                }
            }

        } else {
            System.out.println("SEATING NOT POSSIBLE");
        }
    }

    public static void constructGraph(Scanner s) {
        graph = new Node[Integer.parseInt(s.nextLine())]; //array to store all nodes, length is number of attendants
        numAttendants = graph.length;
        numRivals = Integer.parseInt(s.nextLine()); //quantity of connections needed to be formed

        for (int i = 0; i < numAttendants; i++) {
            graph[i] = new Node(i); //populate graph[] with blank Nodes
        }

        for (int i = 0; i < numRivals; i++) {
            String[] input = s.nextLine().split(" "); //split rival pairs line-by-line i.e. "0 1" -> {0,1}
            int first = Integer.parseInt(input[0]); //for debugging
            int second = Integer.parseInt(input[1]);
            graph[first].addConnection(graph[second]); //create connections to represent rivalries
            graph[second].addConnection(graph[first]); //undirected graph, so include them in both directions
        }
    }

    static void DFS(Node n, int currentSection) {
        n.visited = true;
        n.section = currentSection; //label with current section, alternating between 1 and 2

        //if node has a connection to another node with the same section, set global "seatingPossible" to false
        for (Node neighbor : n.connections) {
            if (!neighbor.visited) {

                DFS(neighbor, 3 - currentSection);
            } else if (neighbor.section == currentSection) {

                seatingPossible = false;
            }
        }
    }
}
