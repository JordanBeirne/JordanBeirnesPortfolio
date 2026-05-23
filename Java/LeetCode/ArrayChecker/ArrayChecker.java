
public class ArrayChecker {

    /*
    LeetCode 1752
    Prompt:
        Given an array nums, return true if the array was originally sorted in non-decreasing order, then rotated some number of positions (including zero). Otherwise, return false.

        There may be duplicates in the original array.
    Solution:
        If an array is sorted, the members exist in non-decreasing order, such that each member is greater than or equal to the last. A rotation to a sorted array allows for up to one 'drop', where a member is less than its predecessor.

        My solution first uses a for loop which iterates through the array and checks each pair of adjacent members. A member's successor is obtained by adding 1 to its index, then reducing the index modulo the array's length, ensuring a check happens between the last and first members.

        The 'drops' are stored as an int, and should this variable exceed 1, the function returns false. Otherwise, once the for loop is finished, the function will default to true.
     */
    public static void main(String[] args) {

        int[] case1 = {3, 4, 5, 1, 2};
        int[] case2 = {2, 1, 3, 4};
        int[] case3 = {1, 2, 3};

        System.out.println(checkForRotation(case1));
        System.out.println(checkForRotation(case2));
        System.out.println(checkForRotation(case3));
    }

    private static boolean checkForRotation(int[] array) {

        int drops = 0;

        for (int i = 0; i < array.length; i++) {
            int nextIndex = (i + 1) % array.length;
            if (array[i] > array[nextIndex]) {
                drops++;
            }
            if (drops > 1) {
                return false;
            }
        }

        return true;
    }
}
