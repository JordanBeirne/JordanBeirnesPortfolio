package com.example.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import com.example.ai.LinkScorer;
import com.example.wiki.WikiService;

public class WikiGameSolver {

    private Node start;
    private String dest;
    private Set<String> targetAdjacent;
    private LinkScorer scorer;
    private static Set<String> failed = new HashSet<>();
    public Node end;
    private List<String> path;

    public WikiGameSolver(Node start, String dest, LinkScorer scorer) {
        this.start = start;
        this.dest = dest;
        this.scorer = scorer;
        this.targetAdjacent = WikiService.getTargetAdjacentArticles(dest);
        List<String> result = BFS(start);
        this.path = result;
    }

    private List<String> BFS(Node startNode) {

        Queue<Node> queue = new PriorityQueue<>(
                Comparator.comparingDouble((Node n) -> -n.score)
                        .thenComparingInt(n -> n.depth)
        );
        Set<String> visited = new HashSet<>();

        queue.add(startNode);
        visited.add(startNode.title);

        while (!queue.isEmpty()) {

            Node current = queue.poll();

            System.out.println("Current: " + current.getPath());

            if (current.depth > 6) {
                continue;
            }

            if (normalize(current.title).equals(normalize(dest))) {
                return current.path;
            }

            if (failed.contains(current.title)) {
                continue;
            }

            try {
                Thread.sleep(4);
            } catch (InterruptedException e) {
            }
            List<String> links = WikiService.getBodyLinks(current.title.trim());

            for (String link : links) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                }
                if (normalize(link).equals(normalize(dest))) {
                    ArrayList<String> foundPath = new ArrayList<>(current.path);
                    foundPath.add(link);
                    return foundPath;
                }

                if (!WikiService.isValidLink(link)) {
                    continue;
                }

                if (!visited.add(link)) {
                    continue;
                }

                ArrayList<String> newPath = new ArrayList<>(current.path);
                newPath.add(link);

                if (current.depth + 1 <= 6) {
                    double localScore = scorer.score(current.title, link, dest);
                    double totalScore = current.score + localScore - (0.12 * current.depth);
                    if (targetAdjacent.contains(link)) {
                        totalScore += 0.25; // 0.15–0.40
                    }
                    queue.add(
                            new Node(link, newPath, current.depth + 1, totalScore)
                    );
                }
            }
        }
        return null;
    }

    private int distanceFromTarget(Node node) {
        return 0;
    }

    private String normalize(String s) {
        return s == null ? "" : s.trim().replace("\u00A0", " ");
    }

    public String getPath() {
        String temp = "";
        for (String s : this.path) {
            temp += s;
            if (!s.equals(dest)) {
                temp += " -> ";
            }
        }
        return temp;
    }

}
