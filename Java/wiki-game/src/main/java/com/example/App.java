package com.example;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);

        // --- Get user input for beginning and end ---
        System.out.println("Enter first article title: ");
        String start = takeInput(kb);

        System.out.println("Enter second article title:");
        String dest = takeInput(kb);
        // ---------------------------------------------

        ArrayList<String> pathOrigin = new ArrayList<>();
        pathOrigin.add(start);
        Node origin = new Node(start, pathOrigin, 0, CategoryMapper.map(start));

        WikiGameSolver solution = new WikiGameSolver(origin, dest);

        if (solution.getPath() == null) {
            System.out.println("No path found.");
        } else {
            System.out.println("Article found! Path: \n" + solution.getPath());
        }

        // for (int i = 0; i < solution.getPath().size(); i++) {
        //     System.out.print(" -> " + solution.getPath().get(i));
        // }
    }

    private static String takeInput(Scanner kb) {
        String input = kb.nextLine();
        if (WikiService.wiki.getTextExtract(input) == null) {
            System.out.println("Article not found. Ensure article name matches exactly. \nTry again: ");
            return takeInput(kb);
        } else {
            System.out.println("Article confirmed: " + input);
            return input;
        }
    }
}
