
import java.util.*;

/* 
Graph 1(c)
    0,1,2,3,4,5,6
0   0,0,0,0,0,0,0
1   0,0,0,1,0,0,0
2   0,1,0,0,0,0,0
3   0,0,1,0,0,0,0
4   1,0,1,0,0,0,0
5   1,1,0,0,1,0,0
6   0,1,1,0,1,0,0

0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,1,0,1,0,0,0,0,1,1,0,0,1,0,0,0,1,1,0,1,0,0 <- Copy this one for input

Graph 1(d)
    0,1,2,3,4,5,6
0   0,0,0,1,0,0,1
1   0,0,0,1,0,0,0
2   0,1,0,0,0,0,0
3   0,0,0,0,0,1,0
4   1,0,0,0,0,0,0
5   1,0,0,0,0,0,0
6   0,1,1,0,1,0,0

0,0,0,1,0,0,1,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,0,1,0,0
 */
public class ReverseGraph {

    static int sourceNode = 6;
    static int[][] initGraph;

    public static void main(String[] args) {
        System.out.println("Input graph, separated by commas:");
        Scanner s = new Scanner(System.in);
        initGraph = constructGraph(s.nextLine());
        int[][] reversedGraph = reverseGraph(initGraph);
        System.out.println("Reversed Graph:\n" + printResults(reversedGraph));
        System.out.println(countOutDegree(reversedGraph));
        System.out.println(countInDegree(reversedGraph));
    }

    static int[][] constructGraph(String s) {
        int currentVal = 0; //track location in splitInput[]
        String[] splitInput = s.split(",");
        double graphsize = Math.sqrt(splitInput.length);
        if ((graphsize % 1) != 0) {
            System.out.println("Invalid Graph input!");
            return null;
        } else {
            int[][] toReturn = new int[(int) graphsize][(int) graphsize];
            for (int i = 0; i < graphsize; i++) {
                for (int k = 0; k < graphsize; k++) {
                    toReturn[i][k] = Integer.parseInt(splitInput[currentVal]);
                    currentVal++;
                }
            }
            return toReturn;
        }
    }

    static int[][] reverseGraph(int[][] g) {
        int[][] reversed = new int[g.length][g.length];
        for (int i = 0; i < g.length; i++) {
            for (int k = 0; k < g[i].length; k++) {
                reversed[i][k] = g[k][i];
            }
        }
        return reversed;
    }

    static String countInDegree(int[][] g) {
        int largest = 0;
        int largestLabel = 0;
        for (int i = 0; i < g.length; i++) {
            int current = 0;
            for (int k = 0; k < g[i].length; k++) {
                current += g[k][i];
            }
            if (current > largest) {
                largest = current;
                largestLabel = i;
            }
        }
        return "Node " + largestLabel + " has largest in degree of: " + largest;
    }

    static String countOutDegree(int[][] g) {
        int largest = 0;
        int largestLabel = 0;
        for (int i = 0; i < g.length; i++) {
            int current = 0;
            for (int k = 0; k < g[i].length; k++) {
                current += g[i][k];
            }
            if (current > largest) {
                largest = current;
                largestLabel = i;
            }
        }
        return "Node " + largestLabel + " has largest out degree of: " + largest;
    }

    static String printResults(int[][] g) {
        String toReturn = "";
        for (int i = 0; i < g.length; i++) {
            toReturn += "[";
            for (int k = 0; k < g[i].length; k++) {
                if (k != g[i].length - 1) {
                    toReturn += g[i][k] + ",";
                } else {
                    toReturn += g[i][k];
                }
            }
            toReturn += "] \n";
        }
        return toReturn;
    }
}
