package core;

import java.util.*;

public class InvertedIndex {
    private Map<String, Set<Integer>> index = new HashMap<>();

    public void addDocument(int docId, List<String> tokens) {
        for (String token : tokens) {
            index.putIfAbsent(token, new HashSet<>());
            index.get(token).add(docId);
        }
    }

    public Set<Integer> getPostingList(String term) {
        return new HashSet<>(index.getOrDefault(term, new HashSet<>()));
    }

    public boolean containsTerm(String term) {
        return index.containsKey(term);
    }

    public Set<String> getVocabulary() {
        return index.keySet();
    }

    public void printSampleIndex(int limit) {
        int count = 0;

        for (Map.Entry<String, Set<Integer>> entry : index.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
            count++;

            if (count >= limit) break;
        }
    }

    public int getVocabularySize() {
        return index.size();
    }
}