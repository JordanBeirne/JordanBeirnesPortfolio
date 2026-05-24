
import java.util.Arrays;

public class Main {

    /*
    LeetCode 1340
    Prompt:
        Given an array of integers arr and an integer d. In one step you can jump from index i to index:

        i + x where: i + x < arr.length and  0 < x <= d.
        i - x where: i - x >= 0 and  0 < x <= d.

        In addition, you can only jump from index i to index j if arr[i] > arr[j] and arr[i] > arr[k] for all indices k between i and j (More formally min(i, j) < k < max(i, j)).

        You can choose any index of the array and start jumping. Return the maximum number of indices you can visit.

        Notice that you can not jump outside of the array at any time.
    Solution:
        My solution revolves around treating the array as a graph and performing Depth-First-Search to explore the possible moves. The DFS algorithm utilizes memoization to avoid re-calculating any given move by storing previous calculations at their corresponding index.

        The DFS algorithm first checks if it has been run previously using the given argument. If it has, it simply returns the value stored at that index in the memo array. Otherwise, it declares a variable to store the current longest move, and evaluates both directions from the current index. For each possible move, we first check if the proposed jump stays within the array. If so, we check if the value at that jump is less than our starting position, as stated by the game rules. If a possible jump is found, the method adds 1 to a recursive call. The addition at the end of the recursive loop provides us with the number of indices visited in that path. The length of the best path is stored as an int and returned once all tests are completed.
     */
    public static void main(String[] args) {

        int[] case1 = {6, 4, 14, 6, 8, 13, 9, 7, 10, 6, 12};
        int d1 = 2;

        int[] case2 = {3, 3, 3, 3, 3};
        int d2 = 3;

        int[] case3 = {7, 6, 5, 4, 3, 2, 1};
        int d3 = 1;

        int[][] cases = {case1, case2, case3};
        int[] dValues = {d1, d2, d3};

        for (int i = 0; i < cases.length; i++) {
            JumpGame game = new JumpGame(cases[i], dValues[i]);
            System.out.println("Input: arr = " + game.printArray(cases[i]) + ", d = " + dValues[i]);
            System.out.println("Maximum jumps: " + game.getMaxJumps());
            System.out.println();
        }
    }
}

class JumpGame {

    private int[] arr;
    private int d;
    private int[] memo;

    public JumpGame(int[] arr, int d) {
        this.arr = arr;
        this.d = d;
        this.memo = new int[arr.length];
    }

    public int getMaxJumps() {

        int max = 1;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, dfs(i));
        }

        return max;
    }

    private int dfs(int index) {

        if (memo[index] != 0) {
            return memo[index];
        }

        int best = 1;

        for (int step = 1; step <= d; step++) {
            int next = index + step;
            if (next >= arr.length) {
                break;
            }
            if (arr[next] >= arr[index]) {
                break;
            }
            best = Math.max(best, 1 + dfs(next));
        }

        for (int step = 1; step <= d; step++) {

            int next = index - step;
            if (next < 0) {
                break;
            }
            if (arr[next] >= arr[index]) {
                break;
            }
            best = Math.max(best, 1 + dfs(next));
        }

        memo[index] = best;

        return best;
    }

    public String printArray(int[] arr) {
        return Arrays.toString(arr);
    }
}
