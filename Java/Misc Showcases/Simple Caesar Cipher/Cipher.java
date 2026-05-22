
import java.util.Scanner;

public class Cipher {

    public static void main(String[] args) throws Exception {
        System.out.println("Enter text to encrypt:\n");
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        String encryptedText = encrypt(input);
        System.out.println("This text encrypted: \n" + encryptedText);
        String decryptedText = decrypt(encryptedText);
        System.out.println("This text decrypted back from the encrypted text: \n" + decryptedText);
    }

    private static String encrypt(String original) {
        char[] splitText = original.toCharArray();
        char[] toReturn = new char[splitText.length];
        char newChar;
        for (int i = 0; i < splitText.length; i++) {
            char c = original.charAt(i);
            if (c != 'z') {
                newChar = (char) (c + 1);
            } else {
                newChar = 'a';
            }
            toReturn[i] = newChar;
        }
        return new String(toReturn);
    }

    private static String decrypt(String original) {
        char[] splitText = original.toCharArray();
        char[] toReturn = new char[splitText.length];
        char newChar;
        for (int i = 0; i < splitText.length; i++) {
            char c = original.charAt(i);
            if (c != 'a') {
                newChar = (char) (c - 1);
            } else {
                newChar = 'z';
            }
            toReturn[i] = newChar;
        }
        return new String(toReturn);
    }
}
