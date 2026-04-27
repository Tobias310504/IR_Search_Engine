package tolerant;

import core.InvertedIndex;
import java.util.Set;

public class EditDistanceStrategy implements TolerantSearchStrategy {
    private int maxDistance;

    public EditDistanceStrategy() {
        // TODO: tentukan threshold edit distance
    }

    @Override
    public Set<Integer> search(String query, InvertedIndex index) {
        // TODO:
        // 1. Cari suggestion dengan edit distance terdekat
        // 2. Jika suggestion ditemukan, return posting list suggestion
        // 3. Jika tidak ditemukan, return hasil kosong
        throw new UnsupportedOperationException("TODO");
    }

    public String suggest(String query, InvertedIndex index) {
        // TODO:
        // 1. Bandingkan query dengan semua term di vocabulary
        // 2. Hitung Levenshtein Distance
        // 3. Pilih term dengan jarak terkecil
        throw new UnsupportedOperationException("TODO");
    }

    private int levenshteinDistance(String a, String b) {
        // TODO:
        // Implementasikan algoritma Levenshtein Distance
        throw new UnsupportedOperationException("TODO");
    }
}