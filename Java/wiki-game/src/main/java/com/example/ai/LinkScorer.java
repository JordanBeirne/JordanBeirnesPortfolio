package com.example.ai;

import java.util.HashMap;
import java.util.Map;

public class LinkScorer {

    private EmbedderService embedder;
    private Map<String, float[]> cache = new HashMap<>();

    private static float[] targetCache = null;

    public LinkScorer(EmbedderService embedder) {
        this.embedder = embedder;
    }

    public double score(String current, String link, String target) {

        try {
            float[] currentEmb = getEmbedding(current);
            float[] linkEmb = getEmbedding(link);
            float[] targetEmb = getEmbedding(TargetCache.getInstance(target).getData());

            double toLink = embedder.cosineSimilarity(currentEmb, linkEmb);
            double toTarget = embedder.cosineSimilarity(linkEmb, targetEmb);

            return (0.4 * toLink) + (0.6 * toTarget);

        } catch (Exception e) {
            return -1;
        }
    }

    private float[] getEmbedding(String text) throws Exception {

        if (cache.containsKey(text)) {
            return cache.get(text);
        }

        float[] embedding = embedder.embed(text);

        cache.put(text, embedding);

        return embedding;
    }
}
