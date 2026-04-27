package core;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class InvertedIndex {
    private Map<String, Set<Integer>> index;

    public InvertedIndex() {
        // TODO: inisialisasi index
    }

    public void addDocument(int docId, List<String> tokens) {
        // TODO:
        // Untuk setiap token:
        // 1. Jika token belum ada di index, buat posting list baru
        // 2. Masukkan docId ke posting list token tersebut
    }

    public Set<Integer> getPostingList(String term) {
        // TODO:
        // Return daftar doc_id untuk term tertentu
        throw new UnsupportedOperationException("TODO");
    }

    public boolean containsTerm(String term) {
        // TODO:
        // Cek apakah term ada di index
        throw new UnsupportedOperationException("TODO");
    }

    public Set<String> getVocabulary() {
        // TODO:
        // Return semua term yang ada di index
        throw new UnsupportedOperationException("TODO");
    }

    public int getVocabularySize() {
        // TODO:
        // Return jumlah term unik
        throw new UnsupportedOperationException("TODO");
    }

    public void printSampleIndex(int limit) {
        // TODO:
        // Print beberapa isi inverted index untuk debugging
    }
}