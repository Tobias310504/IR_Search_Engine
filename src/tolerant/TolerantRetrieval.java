package tolerant;

import core.InvertedIndex;
import java.util.Set;

public class TolerantRetrieval {
    private TolerantSearchStrategy wildcardStrategy;
    private EditDistanceStrategy editDistanceStrategy;

    public TolerantRetrieval() {
        // TODO:
        // Inisialisasi wildcard strategy
        // Inisialisasi edit distance strategy
    }

    public Set<Integer> searchWildcard(String query, InvertedIndex index) {
        // TODO:
        // Panggil wildcard strategy
        throw new UnsupportedOperationException("TODO");
    }

    public Set<Integer> searchWithCorrection(String query, InvertedIndex index) {
        // TODO:
        // Panggil edit distance strategy
        throw new UnsupportedOperationException("TODO");
    }

    public String suggestCorrection(String query, InvertedIndex index) {
        // TODO:
        // Return suggestion dari edit distance strategy
        throw new UnsupportedOperationException("TODO");
    }
}