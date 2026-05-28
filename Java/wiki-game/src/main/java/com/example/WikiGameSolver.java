package com.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Queue;
import java.util.Set;
import java.util.PriorityQueue;

public class WikiGameSolver {

    /*
    Run BFS
    manage queue
    manage visited
    reconstruct path
     */
    private Node start;
    private String dest;
    private List<String> path;
    private Queue queue = new PriorityQueue<>();
    private static Set<String> failed = new HashSet<>();
    public Node end;
    private Category targetCategory;

    public WikiGameSolver(Node start, String dest) {
        this.start = start;
        this.dest = dest;
        this.targetCategory = CategoryMapper.map(WikiService.getCategories(dest));
        if (this.targetCategory == null) {
            this.targetCategory = Category.Technology;
        }

        List<String> result = BFS(start);
        this.path = result;
    }

    private List<String> BFS(Node startNode) {

        Queue<Node> queue = new PriorityQueue<>(
                Comparator.comparingInt(this::distanceFromTarget)
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
            List<String> links = WikiService.getLinks(current.title.trim());

            for (String link : links) {

                System.out.println(current.getPath() + " -> " + link);

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
                    Category cat = CategoryMapper.map(link);
                    queue.add(new Node(link, newPath, current.depth + 1, cat));
                }
            }
        }
        return null;
    }

    private int distanceFromTarget(Node node) {
        Category c = node.category;

        if (c == null || targetCategory == null) {
            return Integer.MAX_VALUE;
        }

        Integer a = CategoryMapper.TARGET_SPACE.get(c);
        Integer b = CategoryMapper.TARGET_SPACE.get(targetCategory);

        System.out.println(node.title + " -> " + node.category + " vs " + targetCategory);

        if (a == null || b == null) {
            return 10;
        }

        return Math.abs(a - b);
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
