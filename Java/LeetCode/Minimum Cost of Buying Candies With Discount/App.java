
import java.util.Arrays;

public class App {

    /*
    LeetCode 2144
    
    Finds the optimal way to handle a "Buy-2-Get-1-Free" discount, given an array of prices, where the free item cannot exceed the minimum price of the first two purchases.

    Fundamentally, we are trying to find which two costs to eliminate from the order. These are constrained by needing two prices greater than the cost which is being eliminated. Therefore, we reverse-sort the array, and apply the discount to every 3 items. All other items get added to the total and returned as the minimum cost of the order.
     */
    int[] cost1 = {1, 2, 3};
    int[] cost2 = {6, 5, 7, 9, 2, 2};

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        System.out.println("Input: " + Arrays.toString(cost1));
        System.out.println("Output:" + optimizeDiscount(cost1));
        System.out.println("Input: " + Arrays.toString(cost2));
        System.out.println("Output:" + optimizeDiscount(cost2));
    }

    private int optimizeDiscount(int[] input) {
        Arrays.sort(input);
        reverseArray(input);
        int total = 0;
        for (int i = 0; i < input.length; i++) {
            if ((i - 2) % 3 != 0) {
                total += input[i];
            }
        }
        return total;
    }

    private void reverseArray(int[] input) {
        for (int i = 0; i < input.length / 2; i++) {
            int temp = input[i];
            input[i] = input[input.length - 1 - i];
            input[input.length - 1 - i] = temp;
        }
    }
}
