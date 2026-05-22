//Author: Jordan Beirne

import java.util.*;

/*
INPUT EXAMPLE 1 (simple increasing)
ABCDE

INPUT EXAMPLE 2 (alternating pattern)
BACAD

INPUT EXAMPLE 3 (strictly decreasing → many stacks needed)
EDCBA

INPUT EXAMPLE 4 (repeated characters)
AAABBBCCC

INPUT EXAMPLE 5 (random mix)
ABACDBECA
 */
class Stack {

    int max;
    int top;
    Character[] storage;

    public Stack(int size) {
        this.top = -1;
        this.storage = new Character[size];
        max = size;
    }

    public Character pop() {
        try {
            Character toReturn = storage[top];
            this.top -= 1;
            return toReturn;
        } catch (Exception e) {
            System.out.println("Stack Error: Stack is empty.");
            return ' ';
        }
    }

    public Character peek() {
        try {
            return storage[top];
        } catch (Exception e) {
            System.out.println("Stack Error: Stack is empty.");
            return ' ';
        }

    }

    public void push(Character c) {
        if (top == max - 1) {
            System.out.println("Stack Error: Stack is full.");
        } else {
            top++;
            storage[top] = c;
        }
    }

}

public class Containers {

    static ArrayList<Stack> result = new ArrayList<>();
    static int maxStackSize = 0;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter Container Order: ");
        String input = s.nextLine();
        maxStackSize = input.length();
        result.add(new Stack(maxStackSize));
        result.get(0).push(input.charAt(0));
        minimumStacks(input);
        System.out.println("Case 1: " + result.size());
    }

    static void minimumStacks(String input) {
        for (int i = 1; i < input.length(); i++) {
            Character c = input.charAt(i);
            boolean placed = false;

            for (int j = 0; j < result.size(); j++) {
                Character top = result.get(j).peek();
                if (c <= top) {
                    result.get(j).push(c);
                    placed = true;
                    break;
                }
            }

            if (!placed) {
                Stack newStack = new Stack(maxStackSize);
                newStack.push(c);
                result.add(newStack);
            }
        }
    }
}
