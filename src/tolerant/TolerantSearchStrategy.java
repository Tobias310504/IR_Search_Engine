package tolerant;

import core.InvertedIndex;
import java.util.Set;

public interface TolerantSearchStrategy {
    Set<Integer> search(String query, InvertedIndex index);
}