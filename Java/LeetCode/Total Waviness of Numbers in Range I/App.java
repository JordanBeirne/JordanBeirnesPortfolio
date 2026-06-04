import java.util.stream.IntStream;

public class App {

    /*
        LeetCode 3751
        
        Finds the "waviness" of a given number n where n >= 100.

        Waviness describes the relationship between the per-digit values of a number, and is represented by its quantity of "peaks" and "valleys". A digit is a peak if it is greater than both of its neighbors, and a valley is where a digit is less than both of its neighbors.

        This program delegates this task into two responsibilities: separating the input number's digits, and calculating its waviness. The number is split into digits by casting it into a String, where we can treat each digit as a separate unit of data, parsing them back to int and returning the digits as an array.

        The function getWaviness loops through each element in the array, excluding the first and last members, checking if it fits either criteria for increasing the number's waviness. Waviness is incremented each time one is detected. For each number in the input range, their waviness is added to the running total. Once each number has been analyzed, the total is returned.
    */

    public static void main(String[] args) {
        int case1num1 = 120;
        int case1num2 = 130;
        int output1 = new App().run(case1num1, case1num2);
        System.out.println("Input: num1 = " + case1num1 + ", num2 = " + case1num2 + "\nOutput: " + output1);

        int case2num1 = 198;
        int case2num2 = 202;
        int output2 = new App().run(case2num1, case2num2);
        System.out.println("Input: num1 = " + case2num1 + ", num2 = " + case2num2 + "\nOutput: " + output2);
    }

    private int run (int num1, int num2) {
        int[] input = IntStream.rangeClosed(num1, num2).toArray();
        int totalWaviness = 0;
        for (int i : input) {
            int[] digits = splitDigits(i);
            totalWaviness += getWaviness(digits);
        }
        return totalWaviness;
    }
        
    private int[] splitDigits (int input) {
        String temp = String.valueOf(input);
        int[] digits = new int[temp.length()];
        String[] tempSplit = temp.split("");

        for (int i = 0; i < temp.length(); i++) {
            digits[i] = Integer.parseInt(tempSplit[i]);
        }

        return digits;
    }

    private int getWaviness (int[] input) {
        int waviness = 0;
        if (input.length < 3) {
            return 0;
        }
        for (int i = 1; i < input.length - 1; i++) {
            int prev = input[i - 1];
            int current = input[i];
            int next = input[i + 1];
            if (
                prev < current && next < current ||
                prev > current && next > current
            ) {
                waviness++;
            }
        }
        return waviness;
    }
    
}