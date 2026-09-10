
public class App {

    /*
    LeetCode #2265

    Given the root of a binary tree, return the number of nodes where the value of the node is equal to the average of the values in its subtree.
    
    */

    static final Tree case1 = new Tree(new Integer[]{4, 8, 5, 0, 1, null, 6});
    static final Tree case2 = new Tree(new Integer[]{1});

    public static void main(String[] args) {
        Solution result1 = new Solution(case1);
        Solution result2 = new Solution(case2);
        System.out.println(result1.output);
        System.out.println(result2.output);
    }
}

class Solution {

    int output;

    public Solution(Tree input) {
        output = averageOfSubtree(input);
    }

    public int averageOfSubtree(Tree input) {

        int count = 0;

        for (int i = 0; i < input.size(); i++) {

            if (input.get(i) == null) {
                continue;
            }

            int average = input.getSubtreeSum(i) / input.getSubtreeSize(i);

            if (input.get(i) == average) {
                count++;
            }
        }

        return count;
    }

}

class Tree {

    private Integer[] list;

    public Tree(Integer[] input) {
        this.list = input;
    }

    public int size() {
        return list.length;
    }

    public Integer get(int index) {
        if (index >= 0 && index < list.length) {
            return list[index];
        } else {
            return 0;
        }
    }

    public Integer getSubtreeSum(int rootIndex) {

        if (rootIndex >= list.length || list[rootIndex] == null) {
            return 0;
        }

        int leftIndex = rootIndex * 2 + 1;
        int rightIndex = rootIndex * 2 + 2;

        int sum = list[rootIndex];

        if (leftIndex < list.length && list[leftIndex] != null) {
            sum += getSubtreeSum(leftIndex);
        }

        if (rightIndex < list.length && list[rightIndex] != null) {
            sum += getSubtreeSum(rightIndex);
        }

        return sum;
    }

    public int getSubtreeSize(int rootIndex) {

        if (rootIndex >= list.length || list[rootIndex] == null) {
            return 0;
        }

        int leftIndex = rootIndex * 2 + 1;
        int rightIndex = rootIndex * 2 + 2;

        int size = 1;

        if (leftIndex < list.length && list[leftIndex] != null) {
            size += getSubtreeSize(leftIndex);
        }

        if (rightIndex < list.length && list[rightIndex] != null) {
            size += getSubtreeSize(rightIndex);
        }

        return size;
    }
}
