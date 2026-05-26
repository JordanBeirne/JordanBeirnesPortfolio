
import java.util.ArrayList;
import java.util.List;

public class Main {

    /*
        LeetCode 3120
        
        Identifies all "special" characters in a String.

        A character is considered special if both its uppercase
        and lowercase forms appear in the input.

        The algorithm first collects all uppercase letters found
        in the String. It then checks whether each uppercase
        letter has a corresponding lowercase occurrence. Matching
        characters are added to the result list and returned.
     */
    public static void main(String[] args) {

        String[] words = {"aaAbcBC", "abc", "abBCab"};

        for (String s : words) {
            System.out.println("Input: " + s + " \n Output: " + format(getSpecialChars(s)));
        }
    }

    private static ArrayList<Character> getSpecialChars(String s) {
        ArrayList<Character> uppercaseLetters = new ArrayList<>();
        ArrayList<Character> specialChars = new ArrayList<>();

        String[] split = s.split("");
        for (String c : split) {
            Character current = c.charAt(0);
            if (Character.isUpperCase(current)) {
                uppercaseLetters.add(current);
            }
        }
        for (Character c : uppercaseLetters) {
            if (s.contains(c.toString().toLowerCase())) {
                specialChars.add(c);
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
