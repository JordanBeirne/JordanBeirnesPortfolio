
import java.util.ArrayList;
import java.util.Collections;

public class App {

    /*
    LeetCode 3161

    This program processes sets of queries which come in two types, denoted by their first index. Type 2 checks for the possibility of building a 'block', or a series of values of a specified length, within a specified range. Type 1 places an 'obstacle' at an index which prohibits the building of a block which intersects this range. All queries are processed by a method which sorts them, as well as tracks the obstacle locations. Type 1 queries have no output, and Type 2 queries output a Boolean value reflecting its ability to build the block.

    This method checks every possible starting position for the block within the range [0, x]. For each possible placement, it examines all obstacles to determine whether any obstacle lies inside the block’s range. Obstacles are allowed to touch the block’s edges, but cannot exist strictly inside the block. If a valid placement is found with no intersecting obstacles, the method returns true. If every possible placement is checked and none are valid, the method returns false.
     */
    private int[][] query1 = {
        {1, 2},
        {2, 3, 3},
        {2, 3, 1},
        {2, 2, 2}
    };

    private int[][] query2 = {
        {1, 7},
        {2, 7, 6},
        {1, 2},
        {2, 7, 5},
        {2, 7, 6}
    };

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {

        ArrayList<Boolean> result1 = process(query1);
        System.out.println(result1);

        ArrayList<Boolean> result2 = process(query2);
        System.out.println(result2);
    }

    private ArrayList<Boolean> process(int[][] queries) {

        ArrayList<Boolean> output = new ArrayList<>();
        ArrayList<Integer> obstacles = new ArrayList<>();

        for (int[] query : queries) {
            if (query[0] == 1) {
                obstacles.add(query[1]);
                Collections.sort(obstacles);
            } else {
                int x = query[1];
                int size = query[2];
                output.add(canPlaceBlock(x, size, obstacles));
            }
        }

        return output;
    }

    private boolean canPlaceBlock(int x, int size, ArrayList<Integer> obstacles) {

        for (int start = 0; start + size <= x; start++) {
            int end = start + size;
            boolean blocked = false;
            for (int obstacle : obstacles) {
                if (obstacle > start && obstacle < end) {
                    blocked = true;
                    break;
                }
            }
            if (!blocked) {
                return true;
            }
        }

        return false;
    }
}
