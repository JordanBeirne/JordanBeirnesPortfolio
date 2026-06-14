package com.example.ai;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDList;
import ai.djl.ndarray.NDManager;
import ai.djl.training.dataset.RandomAccessDataset;
import ai.djl.training.dataset.Record;
import ai.djl.translate.TranslateException;
import ai.djl.util.Progress;
import io.github.fastily.jwiki.core.Wiki;

public class WikipediaPathDataset extends RandomAccessDataset {

    public static final int EMBED_DIM = 384;
    public static final int INPUT_DIM = EMBED_DIM * 3;

    private static final int BFS_DEPTH_LIMIT = 4;

    private final List<float[]> features;
    private final List<Float> labels;

    private WikipediaPathDataset(Builder builder) {
        super(builder);
        this.features = builder.features;
        this.labels = builder.labels;
    }

    @Override
    public Record get(NDManager manager, long index) throws IOException {
        float[] raw = features.get((int) index);
        float lbl = labels.get((int) index);
        NDArray featureArray = manager.create(raw);
        NDArray labelArray = manager.create(new float[]{lbl});
        return new Record(new NDList(featureArray), new NDList(labelArray));
    }

    @Override
    public long availableSize() {
        return features.size();
    }

    @Override
    public void prepare(Progress progress) throws IOException, TranslateException {

    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends BaseBuilder<Builder> {

        final List<float[]> features = new ArrayList<>();
        final List<Float> labels = new ArrayList<>();

        private Wiki wiki;
        private Embedder embedder;
        private int negativesPerPositive = 3;

        @Override
        protected Builder self() {
            return this;
        }

        public Builder setWiki(Wiki wiki) {
            this.wiki = wiki;
            return this;
        }

        public Builder setEmbedder(Embedder embedder) {
            this.embedder = embedder;
            return this;
        }

        public Builder setNegativesPerPositive(int n) {
            this.negativesPerPositive = n;
            return this;
        }

        /**
         * @param articlePairs list of {source, target} string arrays
         */
        public Builder loadFromWikipedia(List<String[]> articlePairs)
                throws TranslateException, IOException {

            if (wiki == null || embedder == null) {
                throw new IllegalStateException("Must set wiki and embedder before loading.");
            }

            Random rng = new Random(42);

            for (String[] pair : articlePairs) {
                String source = pair[0];
                String target = pair[1];

                System.out.println("Finding path: " + source + " → " + target);
                List<String> path = bfsPath(source, target);

                if (path == null) {
                    System.out.println("  No path found within depth limit, skipping.");
                    continue;
                }

                System.out.println("  Path: " + path);
                float[] targetEmbed = embedder.embed(target);

                for (int i = 0; i < path.size() - 1; i++) {
                    String current = path.get(i);
                    String nextLink = path.get(i + 1);

                    float[] currentEmbed = embedder.embed(snippetFor(current));
                    float[] linkEmbed = embedder.embed(nextLink);

                    features.add(concat(currentEmbed, linkEmbed, targetEmbed));
                    labels.add(1.0f);

                    List<String> allLinks = wiki.getLinksOnPage(current);
                    allLinks.remove(nextLink);
                    Collections.shuffle(allLinks, rng);

                    int negCount = Math.min(negativesPerPositive, allLinks.size());
                    for (int n = 0; n < negCount; n++) {
                        float[] negEmbed = embedder.embed(allLinks.get(n));
                        features.add(concat(currentEmbed, negEmbed, targetEmbed));
                        labels.add(0.0f);
                    }
                }
            }

            return this;
        }

        public WikipediaPathDataset build() {
            if (features.isEmpty()) {
                throw new IllegalStateException("No data loaded. Call loadFromWikipedia() before build().");
            }
            return new WikipediaPathDataset(this);
        }

        private List<String> bfsPath(String source, String target) {
            // Each queue entry is the path taken so far
            Queue<List<String>> queue = new LinkedList<>();
            Set<String> visited = new HashSet<>();

            queue.add(new ArrayList<>(List.of(source)));
            visited.add(source);

            while (!queue.isEmpty()) {
                List<String> currentPath = queue.poll();
                String currentArticle = currentPath.get(currentPath.size() - 1);

                if (currentPath.size() - 1 >= BFS_DEPTH_LIMIT) {
                    continue;
                }

                List<String> links = wiki.getLinksOnPage(currentArticle);
                for (String link : links) {
                    if (visited.contains(link)) {
                        continue;
                    }
                    visited.add(link);

                    List<String> newPath = new ArrayList<>(currentPath);
                    newPath.add(link);

                    if (link.equals(target)) {
                        return newPath;
                    }
                    queue.add(newPath);
                }
            }
            return null; 
        }

        private String snippetFor(String articleTitle) {
            try {
                String text = wiki.getTextExtract(articleTitle);
                if (text == null || text.isBlank()) {
                    return articleTitle;
                }
                return text.length() > 500 ? text.substring(0, 500) : text;
            } catch (Exception e) {
                return articleTitle;
            }
        }

        private float[] concat(float[] a, float[] b, float[] c) {
            float[] result = new float[a.length + b.length + c.length];
            System.arraycopy(a, 0, result, 0, a.length);
            System.arraycopy(b, 0, result, a.length, b.length);
            System.arraycopy(c, 0, result, a.length + b.length, c.length);
            return result;
        }
    }

    @FunctionalInterface
    public interface Embedder {

        float[] embed(String text) throws TranslateException;
    }
}
