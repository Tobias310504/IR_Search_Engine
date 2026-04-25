package core;

import preprocessing.Preprocessor;
import booleanquery.BooleanEngine;
import tolerant.TolerantRetrieval;
import util.TextUtil;

import java.util.*;

public class SearchEngine {
    private DocumentStore documentStore;
    private Preprocessor preprocessor;
    private InvertedIndex invertedIndex;
    private BooleanEngine booleanEngine;
    private TolerantRetrieval tolerantRetrieval;

    public SearchEngine(Preprocessor preprocessor) {
        this.documentStore = new DocumentStore();
        this.preprocessor = preprocessor;
        this.invertedIndex = new InvertedIndex();
        this.booleanEngine = new BooleanEngine(invertedIndex);
        this.tolerantRetrieval = new TolerantRetrieval();
    }

    public void buildIndex(String filePath) {
        documentStore.loadFromFile(filePath);

        for (Document document : documentStore.getAllDocuments()) {
            List<String> tokens = preprocessor.process(document.getContent());
            invertedIndex.addDocument(document.getId(), tokens);
        }

        System.out.println("Index built successfully.");
        System.out.println("Total documents: " + documentStore.size());
        System.out.println("Total unique terms: " + invertedIndex.getVocabularySize());
    }

    public Set<Integer> search(String query) {
        query = query.trim();

        if (query.isEmpty()) {
            return new HashSet<>();
        }

        if (TextUtil.containsWildcard(query)) {
            return tolerantRetrieval.searchWildcard(query, invertedIndex);
        }

        if (TextUtil.containsBooleanOperator(query)) {
            return booleanEngine.search(query, documentStore.getAllDocumentIds());
        }

        String normalizedTerm = TextUtil.normalizeToken(query);

        if (invertedIndex.containsTerm(normalizedTerm)) {
            return invertedIndex.getPostingList(normalizedTerm);
        }

        System.out.println("Term not found: " + query);
        return tolerantRetrieval.searchWithCorrection(query, invertedIndex);
    }

    public void printResults(Set<Integer> resultIds) {
        if (resultIds.isEmpty()) {
            System.out.println("No documents found.");
            return;
        }

        List<Integer> sortedIds = new ArrayList<>(resultIds);
        Collections.sort(sortedIds);

        System.out.println("Found " + sortedIds.size() + " document(s):");

        for (Integer id : sortedIds) {
            Document document = documentStore.getDocumentById(id);

            if (document != null) {
                System.out.println("-----------------------------------");
                System.out.println("Doc " + document.getId() + " - " + document.getTitle());
                System.out.println(document.getContent());
            }
        }
    }

    public void printSampleIndex() {
        invertedIndex.printSampleIndex(20);
    }
}