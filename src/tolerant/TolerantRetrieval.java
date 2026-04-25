package tolerant;

import core.InvertedIndex;
import java.util.Set;

public class TolerantRetrieval {
    private TolerantSearchStrategy wildcardStrategy;
    private EditDistanceStrategy editDistanceStrategy;

    public TolerantRetrieval() {
        this.wildcardStrategy = new WildcardSearchStrategy();
        this.editDistanceStrategy = new EditDistanceStrategy();
    }

    public Set<Integer> searchWildcard(String query, InvertedIndex index) {
        return wildcardStrategy.search(query, index);
    }

    public Set<Integer> searchWithCorrection(String query, InvertedIndex index) {
        return editDistanceStrategy.search(query, index);
    }

    public String suggestCorrection(String query, InvertedIndex index) {
        return editDistanceStrategy.suggest(query, index);
    }
}