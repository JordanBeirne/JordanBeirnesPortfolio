package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.github.fastily.jwiki.core.Wiki;

public class WikiService {

    /*
    Call Wikipedia API via JWiki
    Cache link lookups
     */
    public static Wiki wiki = new Wiki.Builder()
            .withDomain("en.wikipedia.org")
            .build();
    private static Map<String, ArrayList<String>> cache = new HashMap<String, ArrayList<String>>();
    private static long lastCallTime = 0;
    private static final int MIN_DELAY_MS = 50;

    //String text = wiki.getTextExtract("Java (programming language)");
    //ArrayList<String> links = wiki.getLinksOnPage("Java (programming language)");
    public static ArrayList<String> getLinks(String title) {

        if (title == null || title.isBlank()) {
            return new ArrayList<>();
        }

        if (cache.containsKey(title)) {
            return cache.get(title);
        }

        int attempts = 0;

        while (attempts < 3) {
            try {
                long now = System.currentTimeMillis();
                long diff = now - lastCallTime;

                if (diff < MIN_DELAY_MS) {
                    Thread.sleep(MIN_DELAY_MS - diff);
                }

                lastCallTime = System.currentTimeMillis();

                ArrayList<String> links = wiki.getLinksOnPage(title);

                if (links == null) {
                    links = new ArrayList<>();
                }

                cache.put(title, links);
                return links;

            } catch (Exception e) {
                attempts++;

                try {
                    Thread.sleep(100 * attempts); // exponential backoff
                } catch (InterruptedException ignored) {
                }
            }
        }

        System.out.println("Skipping page after failures: " + title);
        return new ArrayList<>();
    }

    public static boolean isValidLink(String link) {

        if (link == null || link.isBlank()) {
            return false;
        }

        String lower = link.toLowerCase();

        // filter out Wikipedia utility links
        if (lower.startsWith("wikipedia:")
                || lower.startsWith("help:")
                || lower.startsWith("template:")
                || lower.startsWith("portal:")
                || lower.startsWith("category:")
                || lower.startsWith("special:")
                || lower.startsWith("file:")
                || lower.startsWith("draft:")
                || lower.startsWith("module:")
                || lower.startsWith("template talk:")
                || lower.startsWith("wikipedia talk:")) {
            return false;
        }

        // filter out links from excluded parts of the page
        if (lower.contains("isbn")
                || lower.contains("doi")
                || lower.contains("citation")
                || lower.contains("bibcode")
                || lower.contains("wayback machine")
                || lower.contains("issn")) {
            return false;
        }
        // filter out date pages
        if (lower.matches(".*\\b\\d{4}\\b.*")) {
            return false;
        }
        // filter out list utility pages
        if (lower.startsWith("list of")) {
            return false;
        }

        return true;
    }

    public static List<String> cleanCategories(List<String> raw) {

        List<String> result = new ArrayList<>();

        for (String c : raw) {

            if (c == null) {
                continue;
            }

            String cat = c.replace("Category:", "").trim();
            String lower = cat.toLowerCase();

            if (lower.contains("articles needing")
                    || lower.contains("articles with")
                    || lower.contains("cs1")
                    || lower.contains("wikipedia")
                    || lower.contains("pages using")
                    || lower.contains("template")
                    || lower.contains("short description")
                    || lower.contains("use mdy")
                    || lower.contains("unsourced")
                    || lower.contains("cleanup")
                    || lower.contains("disputed")) {
                continue;
            }

            result.add(cat);
        }

        return result;
    }

    public static Set<String> getSuperCategories(String title) {

        List<String> raw = categoriesList(title);
        List<String> clean = cleanCategories(raw);

        Set<String> mapped = new HashSet<>();

        for (String c : clean) {
            Category m = CategoryMapper.map(c);
            if (m != null) {
                mapped.add(c);
            }
        }

        return mapped;
    }

    public static String getCategories(String title) {
        String temp = "";
        for (String s : wiki.getCategoriesOnPage(title)) {
            temp += s;
        }

        return temp;

    }

    public static List<String> categoriesList(String title) {
        return wiki.getCategoriesOnPage(title);
    }

}
