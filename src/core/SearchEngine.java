package core;

import preprocessing.Preprocessor;
import booleanquery.BooleanEngine;
import tolerant.TolerantRetrieval;

import java.util.Set;

public class SearchEngine {
    private DocumentStore documentStore;
    private Preprocessor preprocessor;
    private InvertedIndex invertedIndex;
    private BooleanEngine booleanEngine;
    private TolerantRetrieval tolerantRetrieval;

    public SearchEngine(Preprocessor preprocessor) {
        // TODO:
        // Inisialisasi semua komponen:
        // DocumentStore
        // Preprocessor
        // InvertedIndex
        // BooleanEngine
        // TolerantRetrieval
    }

    public void buildIndex(String filePath) {
        // TODO:
        // 1. Load dokumen dari file
        // 2. Untuk setiap dokumen:
        //    - ambil content
        //    - preprocess content
        //    - masukkan token ke inverted index
    }

    public Set<Integer> search(String query) {
        // TODO:
        // 1. Jika query wildcard, pakai tolerant wildcard
        // 2. Jika query Boolean, pakai BooleanEngine
        // 3. Jika query single term, pakai posting list biasa
        // 4. Jika term tidak ditemukan, pakai edit distance
        throw new UnsupportedOperationException("TODO");
    }

    public void printResults(Set<Integer> resultIds) {
        // TODO:
        // 1. Jika result kosong, tampilkan "No documents found"
        // 2. Jika ada, tampilkan doc_id, title, dan content
    }

    public void printSampleIndex() {
        // TODO:
        // Panggil method print sample dari InvertedIndex
    }
}