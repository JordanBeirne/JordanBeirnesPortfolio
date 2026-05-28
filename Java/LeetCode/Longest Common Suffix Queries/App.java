
public class App {

    /*
    LeetCode 3093

    Finds which member of wordsContainer has the longest common suffix with the current member of wordsQuery. Two Strings are considered to have a common suffix if their ending characters match.

    Uses a nested for loop to run the program once for each member of wordsQuery, and compare them with each member of wordsContainer. The value of the largest suffix is stored in the outer for loop, with its corresponding index in wordsContainer. These variables are overwritten when a better match is found in the inner for loop. Once both loops have completed, the result array has received all optimal values and is returned.

    The logic for finding the length of a common suffix is handled by the helper method getSuffixLength
     */
    static String[] wordsContainer1 = {"abcd", "bcd", "xbcd"};
    static String[] wordsQuery1 = {"cd", "bcd", "xyz"};

    static String[] wordsContainer2 = {"abcdefgh", "poiuygh", "ghghgh"};
    static String[] wordsQuery2 = {"gh", "acbfgh", "acbfegh"};

    public static void main(String[] args) {
        int[] result1 = new App().run(wordsContainer1, wordsQuery1);
        System.out.println(java.util.Arrays.toString(result1));
        int[] result2 = new App().run(wordsContainer2, wordsQuery2);
        System.out.println(java.util.Arrays.toString(result2));
    }

    private int[] run(String[] wordsContainer, String[] wordsQuery) {

        int[] result = new int[wordsQuery.length];

        for (int i = 0; i < wordsQuery.length; i++) {
            String query = wordsQuery[i];
            int largestSuffix = 0;
            int bestIndex = 0;

            for (int j = 0; j < wordsContainer.length; j++) {
                String container = wordsContainer[j];
                int currentSuffix = getSuffixLength(container, query);
                if (currentSuffix > largestSuffix) {
                    largestSuffix = currentSuffix;
                    bestIndex = j;
                } else if (currentSuffix == largestSuffix && container.length() < wordsContainer[bestIndex].length()) {
                    bestIndex = j;
                }
            }

            result[i] = bestIndex;
        }
        return result;
    }

    private int getSuffixLength(String container, String query) {
        int count = 0;
        int i = container.length() - 1;
        int j = query.length() - 1;

        while (i >= 0 && j >= 0 && container.charAt(i) == query.charAt(j)) {
            count++;
            i--;
            j--;
        }
        return count;
    }
}
