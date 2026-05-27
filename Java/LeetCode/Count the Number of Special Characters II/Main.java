
import java.util.ArrayList;
import java.util.List;

public class Main {

    /*
        LeetCode 3121
        
        Finds number of special characters in a String where:
        
            A letter c is called special if it appears both in lowercase and uppercase in word, and every lowercase occurrence of c appears before the first uppercase occurrence of c.

        Scans the array for all appearances of uppercase letters. If a letter is uppercase, it checks for its lowercase counterpart while ensuring its index is below that of the uppercase letter. It then iterates over the rest of the String, starting with the uppercase letter's index, to check for another occurance of the lowercase letter, which would render the letter no longer special.

        Returns the resulting ArrayList of characters. Number of special characters is accessed via the size of this List.
     */
    public static void main(String[] args) {

        String[] words = {"aaAbcBC", "abc", "abBCab"};

        for (String s : words) {
            ArrayList<Character> result = getSpecialChars(s);
            System.out.println("Input: " + s + " \n Output: " + result.size() + "\n" + format(result));
        }
    }

    private static ArrayList<Character> getSpecialChars(String s) {

        ArrayList<Character> specialChars = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (Character.isUpperCase(current)) {
                char lower = Character.toLowerCase(current);
                if (s.contains(lower + "") && s.indexOf(lower) < s.indexOf(current)) {
                    boolean valid = true;
                    for (int j = s.indexOf(current); j < s.length(); j++) {
                        if (s.charAt(j) == lower) {
                            valid = false;
                            break;
                        }
                    }
                    if (valid && !specialChars.contains(lower)) {
                        specialChars.add(lower);
                    }
                }
            }
        }
        return specialChars;
    }

    private static String format(List<Character> input) {
        if (input.isEmpty()) {
            return "No character in word is special";
        }
        String result = "";
        for (Character c : input) {
            result += "'" + c + "', ";
        }
        return result.substring(0, result.length() - 2);
    }

}
