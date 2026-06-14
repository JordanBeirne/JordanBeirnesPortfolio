package com.example;

import java.util.ArrayList;
import java.util.Scanner;

import com.example.ai.EmbedderService;
import com.example.ai.LinkScorer;
import com.example.graph.Node;
import com.example.graph.WikiGameSolver;
import com.example.wiki.WikiService;

public class App {

    public static void main(String[] args) throws Exception {
        java.util.logging.Logger.getLogger("io.github.fastily.jwiki.core.Wiki").setLevel(java.util.logging.Level.OFF);
        java.util.logging.Logger.getLogger("ai.djl").setLevel(java.util.logging.Level.OFF);

        Scanner kb = new Scanner(System.in);

        System.out.println("Enter first article title: ");
        String start = takeInput(kb);

        System.out.println("Enter second article title:");
        String dest = takeInput(kb);

        ArrayList<String> pathOrigin = new ArrayList<>();
        pathOrigin.add(start);
        Node origin = new Node(start, pathOrigin, 0, 0);

        EmbedderService embedder = new EmbedderService();

        LinkScorer scorer = new LinkScorer(embedder);
        WikiGameSolver solution = new WikiGameSolver(origin, dest, scorer);

        if (solution.getPath() == null) {
            System.out.println("No path found.");
        } else {
            System.out.println("Article found! Path: \n" + solution.getPath());
        }
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
