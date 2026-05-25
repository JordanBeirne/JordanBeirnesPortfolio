
public class Main {

    /*
    LeetCode 1871
    Prompt:
        You are given a 0-indexed binary string s and two integers minJump and maxJump. In the beginning, you are standing at index 0, which is equal to '0'. You can move from index i to index j if the following conditions are fulfilled:

            i + minJump <= j <= min(i + maxJump, s.length - 1), and
            s[j] == '0'.

        Return true if you can reach index s.length - 1 in s, or false otherwise.

    Solution:
        My solution solves the prompt by navigating the String with recursion, exploring the possible jump distances between the minimum jump and maximum jump values.

        First, we check if this index has already been evaluated, returning its value from the memo array if so. This avoids any repeated calculations by caching previously solved subproblems, improving computation time by using slightly more memory. 
        
        Next, we see if the game is solved, by checking whether our current index is at the end of the String, returning true if it is. Our last base case is if our next minimum jump exceeds the String's length, returning false if needed. 
        
        Otherwise, we iterate through all possible jump distances between minJump and maxJump. For each jump, we first ensure the next index stays within the bounds of the String. If it does, we check whether the destination contains a '0', indicating a valid landing position. We then recursively explore that path. If any recursive call reaches the end successfully, the method returns true.

        The last possibility is that no jump to a "0" exists within the minJump and maxJump values from the current index. If this is the case, we cache the result in the memo array and return false.
     */
    public static void main(String[] args) {
        String s1 = "011010";
        int minJump1 = 2;
        int maxJump1 = 3;

        String s2 = "01101110";
        int minJump2 = 2;
        int maxJump2 = 3;

        JumpGame game1 = new JumpGame(s1, minJump1, maxJump1);
        JumpGame game2 = new JumpGame(s2, minJump2, maxJump2);

        System.out.println("Input: \"" + game1.s + "\", minJump = " + game1.minJump + ", maxJump = " + game1.maxJump);
        System.out.println("Output: " + game1.getOutput());
        System.out.println("Input: \"" + game2.s + "\", minJump = " + game2.minJump + ", maxJump = " + game2.maxJump);
        System.out.println("Output: " + game2.getOutput());
    }

}

class JumpGame {

    private Boolean output;
    public String s;
    public int minJump;
    public int maxJump;
    private Boolean[] memo;

    public JumpGame(String s, int minJump, int maxJump) {
        this.s = s;
        this.minJump = minJump;
        this.maxJump = maxJump;
        this.memo = new Boolean[s.length()];
        this.output = play(0);
    }

    private Boolean play(int i) {

        if (memo[i] != null) {
            return memo[i];
        }

        if (i == s.length() - 1) {
            memo[i] = true;
            return true;
        }

        if (i + minJump > s.length() - 1) {
            memo[i] = false;
            return false;
        }

        for (int j = minJump; j <= maxJump; j++) {

            if (i + j >= s.length()) {
                break;
            }

            if (s.charAt(i + j) == '0' && play(i + j)) {
                memo[i] = true;
                return true;
            }
        }

        memo[i] = false;
        return false;

    }

    public Boolean getOutput() {
        return this.output;
    }

}
