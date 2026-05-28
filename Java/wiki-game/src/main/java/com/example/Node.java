package com.example;

import java.util.ArrayList;

public class Node { // Purpose: State object

    String title;
    ArrayList<String> path;
    int depth;
    Category category;

    public Node(String title, ArrayList<String> path, int depth, Category category) {
        this.title = title.trim();
        this.depth = depth;
        this.path = new ArrayList<>(path);
        this.category = category;
    }

    public String getPath() {
        String temp = "";
        for (String s : this.path) {
            temp += " - > " + s;
        }
        return temp;
    }

}
