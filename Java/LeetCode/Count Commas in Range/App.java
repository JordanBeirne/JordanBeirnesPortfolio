public class App {
    /*
        Leetcode #3870

        You are given an integer n.

        Return the total number of commas used when writing all integers from [1, n] (inclusive) in standard number formatting.

        In standard formatting:

            A comma is inserted after every three digits from the right.
            Numbers with fewer than 4 digits contain no commas.

    */
    
    private final int case1 = 1002;
    private final int case2 = 998;
    
    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        System.out.println(solve(case1));
        System.out.println(solve(case2));

    }

    private int solve(int input) {
        int totalCommas = 0;
        for (int i = 1000; i <= input; i++) {
            totalCommas += getNumberOfCOmmas(i);
        }
        return totalCommas;
    }

    private int getNumberOfCOmmas(int num) {
        int commas = 0;
        num -= num % 1000;
        if (num < 1000) {
            return 0;
        }
        else {
            commas++;
            return commas + solve(num / 1000);
        }
    }
}
