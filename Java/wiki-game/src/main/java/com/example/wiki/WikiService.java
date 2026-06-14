package com.example.wiki;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import io.github.fastily.jwiki.core.Wiki;

public class WikiService {

    private static final String BASE =
            "https://en.wikipedia.org/wiki/";

    public static Wiki wiki = new Wiki.Builder()
            .withDomain("en.wikipedia.org")
            .build();

    private static Map<String, ArrayList<String>> cache = new HashMap<>();

    private static final Map<String, Set<String>> targetAdjCache = new HashMap<>();


    private static final int TIMEOUT_MS = 8000;

    private static Document fetch(String title) throws Exception {
        String url = BASE + title.replace(" ", "_");

        return Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (WikiGameBot)")
                .timeout(TIMEOUT_MS)
                .get();
    }

    public static ArrayList<String> getLinks(String title) {

        if (title == null || title.isBlank()) {
            return new ArrayList<>();
        }

        if (cache.containsKey(title)) {
            return cache.get(title);
        }

        ArrayList<String> result = new ArrayList<>();

        try {

            Document doc = fetch(title);

            Element content = doc.selectFirst("#mw-content-text");

            if (content == null) {
                return result;
            }

            content.select(
                    ".navbox, .reflist, .infobox, .metadata, " +
                    ".vertical-navbox, .sidebar, table"
            ).remove();

            Elements headers = content.select("h2, h3");

            for (Element h : headers) {
                String text = h.text().toLowerCase();

                if (text.contains("external links")
                        || text.contains("references")
                        || text.contains("further reading")) {
                    h.nextElementSiblings().forEach(Element::remove);
                    break;
                }
            }

            Elements links = content.select("p a[href^=/wiki/]");

            for (Element link : links) {

                String href = link.attr("href");

                if (href.contains(":")) continue;

                String clean = href.replace("/wiki/", "")
                                   .replace("_", " ");

                if (isValidLink(clean)) {
                    result.add(clean);
                }
            }

        } catch (Exception e) {
            System.out.println("Failed page: " + title);
        }

        cache.put(title, result);
        return result;
    }

    public static ArrayList<String> getBodyLinks(String title) {

    ArrayList<String> result = new ArrayList<>();

    try {
        String url = "https://en.wikipedia.org/wiki/" + title.replace(" ", "_");

        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0")
                .timeout(5000)
                .get();

        Element content = doc.selectFirst("#mw-content-text .mw-parser-output");

        if (content == null) return result;

        for (Element el : content.children()) {

            if (el.tagName().equals("h2")) {
                String heading = el.text().toLowerCase();

                if (heading.contains("external links")
                        || heading.contains("references")
                        || heading.contains("further reading")
                        || heading.contains("see also")) {
                    break;
                }
            }

            Elements links = el.select("a[href]");

            for (Element a : links) {

                String href = a.attr("href");

                if (!href.startsWith("/wiki/")) continue;
                if (href.contains(":")) continue; // wiki/meta pages

                String clean = href.replace("/wiki/", "").replace("_", " ");

                result.add(clean);
            }
        }

    } catch (IOException e) {
        e.printStackTrace();
    }

    return result;
    }

    public static boolean isValidLink(String link) {

        if (link == null || link.isBlank()) return false;

        String lower = link.toLowerCase();

        if (lower.startsWith("wikipedia:")
                || lower.startsWith("help:")
                || lower.startsWith("file:")
                || lower.startsWith("category:")
                || lower.startsWith("special:")
                || lower.startsWith("template:")
                || lower.startsWith("portal:")
                || lower.startsWith("list of")) {
            return false;
        }

        if (lower.matches(".*\\b\\d{4}\\b.*")) return false;

        return true;
    }

    public static List<String> cleanCategories(List<String> raw) {
        return raw;
    }


    public static Set<String> getTargetAdjacentArticles(String target) {

        return targetAdjCache.computeIfAbsent(target, t -> {
            try {
                List<String> incoming = wiki.whatLinksHere(t);

                if (incoming == null) {
                    return new HashSet<>();
                }

                return new HashSet<>(incoming);

            } catch (Exception e) {
                return new HashSet<>();
            }
        });
    }
}