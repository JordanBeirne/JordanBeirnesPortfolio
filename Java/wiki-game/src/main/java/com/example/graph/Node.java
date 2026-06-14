package com.example.graph;

import java.util.ArrayList;

public class Node {

    String title;
    ArrayList<String> path;
    int depth;
    double score;

    public Node(String title, ArrayList<String> path, int depth, double score) {
        this.title = title.trim();
        this.depth = depth;
        this.path = new ArrayList<>(path);
        this.score = score;
    }

    public String getPath() {
        String temp = "";
        for (String s : this.path) {
            temp += " - > " + s;
        }
        return temp;
    }

}
