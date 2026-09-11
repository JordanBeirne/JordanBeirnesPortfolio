import java.util.Arrays;

/*
    LeetCode #3483

    You are given an array of digits called digits. Your task is to determine the number of distinct three-digit even numbers that can be formed using these digits.

    Note: Each copy of a digit can only be used once per number, and there may not be leading zeros
*/

public class App {
    static final int[] case1 = {1,2,3,4};
    static final int[] case2 = {0,2,2};
    static final int[] case3 = {6,6,6};
    static final int[] case4 = {1,3,5};

    static final int[][] cases = {case1, case2, case3, case4};

    public static void main(String[] args) {

        Solution output = new Solution();

        for(int[] digits : cases) {
            System.out.println(output.totalNumbers(digits));
        }
    }

}

class Solution {
    public int totalNumbers(int[] digits) {
        // get number of unique numbers
        int uniqueNums = Math.toIntExact(Arrays.stream(digits).distinct().count());

        // factorial of unique numbers
        int factorial = 1;
        for (int i = uniqueNums; i > 0; i--) {
            factorial *= i;
        }

        // find the number of odd members
        int numOdd = 0;
        for(int i = 0; i < digits.length; i++) {
            if(digits[i] % 2 != 0) numOdd++;
        }

        // get factorial of number of odd members
        int oddFactorial = 1;
        for (int i = numOdd; i > 0; i--) {
            oddFactorial *= i;
        }

        // divide by number of odd members
        if(digits.length == numOdd) {
            return 0;
        }
        else return factorial / oddFactorial;
        
    }
}