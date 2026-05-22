import java.util.*;

class Node{
    int label;
    Node prev;
    boolean visited;
    public Node(int label){
        this.label = label;
        this.visited = false;
    }
}

public class myDFS {
    static int sourceNode = 6;
    static int[][] graph = {
        //   0,1,2,3,4,5,6,7
            {0,0,1,1,1,1,0,0},//0
            {0,0,0,0,1,1,1,0},//1
            {0,0,0,1,1,0,0,0},//2
            {0,0,0,0,0,1,0,1},//3
            {0,0,0,0,0,1,0,0},//4
            {0,0,0,0,0,0,0,0},//5
            {0,0,0,0,0,1,0,0},//6
            {0,0,0,0,0,1,1,0},//7
    };
    static Node[] nodeList = {
        new Node(0),
        new Node(1),
        new Node(2),
        new Node(3),
        new Node(4),
        new Node(5),
        new Node(6),
        new Node(7),
    };
    static ArrayList<Node> result = new ArrayList<>();
    public static void main(String[] args) {
        result.add(nodeList[sourceNode]);
        DFS(nodeList[sourceNode]);
        String toPrint = "";
        for(int i = 0; i < result.size(); i++){
            toPrint += result.get(i).label + " ";
        }
        System.out.println("Resulting Path: " + toPrint);
    }

    static void DFS(Node n){
        n.visited = true;
        for(int i = 0; i < 8; i++){
            if(graph[n.label][i] == 1){
                if(!nodeList[i].visited){
                    result.add(nodeList[i]);
                    DFS(nodeList[i]);
                }
            }
        }
    }
    
}
