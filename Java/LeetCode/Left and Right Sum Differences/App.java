import java.util.Arrays;

public class App {

    /*
    LeetCode 2574

    Uses prefix sums and suffix sums to determine the absolute difference between the sums of elements on the left and right side of each array index. In the output array, each value represents the result of adding each element before that index, subtracts by the result of adding each element after that index.

    Finds the left sums by iterating over each element in the input array, adding the current element to the sum of the previous index and storing this value at the same index in an array which is returned. To avoid re-calculating the sum of all members before a given index, factorially increasing the time complexity of this program, this function references the previous member in the sums array. 

    Builds an array of right sums in an identical, but reversed, structure which iterates over the array in reverse order.

    Calculates final result by build an array where each element represents the difference between the elements at that index in the leftSum and rightSum arrays. This array is returned as the program's output.
    */

    public static void main (String[] args) {
        new App().run();
    }

    private void run() {
        int[] nums1 = {10,4,8,3};
        int[] output1 =
        findDifference(findLeftSum(nums1), findRightSum(nums1));
        System.out.println("Input: " + Arrays.toString(nums1) + "\nOutput: " + Arrays.toString(output1));
        int[] nums2 = {1};
        int[] output2 = 
        findDifference(findLeftSum(nums2), findRightSum(nums2));
                System.out.println("Input: " + Arrays.toString(nums2) + "\nOutput: " + Arrays.toString(output2));

    }

    private int[] findLeftSum(int[] input) {
        int[] sums = new int[input.length];

        for (int i = 1; i < input.length; i++) {
            sums[i] = sums[i - 1] + input[i - 1];
        }

        return sums;
    }

    private int[] findRightSum(int[] input) {
        int[] sums = new int[input.length];

        for (int i = input.length - 2; i >= 0; i--) {
            sums[i] = sums[i + 1] + input[i + 1];
        }

        return sums;
    }

    private int[] findDifference(int[] leftSum, int[] rightSum) {
        int[] diff = new int[leftSum.length];

        for (int i = 0; i < diff.length; i++) {
            diff[i] = Math.abs(leftSum[i] - rightSum[i]);
        }

        return diff;
    }
}