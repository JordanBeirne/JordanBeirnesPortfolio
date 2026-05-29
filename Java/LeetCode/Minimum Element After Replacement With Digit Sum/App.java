
import java.util.Arrays;

public class App {

    /*
    LeetCode 3300
    
    Adds the digits of each number and outputs the smallest resulting sum.

    Iterates through array of inputs, running sumDigits on each member.

    sumDigits reduces input int modulo 10, adding the result of this expression to integer "sum". Input is decreased by the same amount, then divided by 10. Loop continues until Input reaches 0.

    min exists as a helper method to find the minimum member of an array.
     */
    public static void main(String[] args) {
        int[][] inputs = {
            {10, 12, 13, 14},
            {1, 2, 3, 4},
            {999, 19, 199}
        };
        for (int[] input : inputs) {
            System.out.println("Input: "
                    + Arrays.toString(input)
                    + "\nOutput: "
                    + new App().run(input));
        }
    }

    private int run(int[] input) {
        int[] temp = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            temp[i] = sumDigits(input[i]);
        }
        return min(temp);
    }

    private int sumDigits(int input) {
        int sum = 0;
        while (input > 0) {
            int digit = input % 10;
            sum += digit;
            input -= digit;
            input /= 10;
        }

        return sum;
    }

    private int min(int[] input) {
        int min = input[0];
        for (int i : input) {
            if (i < min) {
                min = i;
            }
        }
        return min;
    }
}
