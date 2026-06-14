package com.example.ai;

import com.example.wiki.WikiService;

public class TargetCache {

    private static TargetCache instance;
    private final String data;

    private TargetCache(String target) {
        this.data = WikiService.wiki.getTextExtract(target);
    }

    public static TargetCache getInstance(String target) {
        if (instance == null) {
            instance = new TargetCache(target);
        }
        return instance;
    }

    public String getData() {
        return data;
    }
}
