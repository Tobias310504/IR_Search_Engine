package tolerant;

import core.InvertedIndex;
import util.TextUtil;
import java.util.*;

public class WildcardSearchStrategy implements TolerantSearchStrategy {

    @Override
    public Set<Integer> search(String query, InvertedIndex index) {
        Set<Integer> result = new HashSet<>();

        String normalizedQuery = query.toLowerCase().trim();

        if (!normalizedQuery.endsWith("*")) {
            System.out.println("This simple wildcard strategy only supports prefix wildcard, example: comput*");
            return result;
        }

        String prefix = normalizedQuery.substring(0, normalizedQuery.length() - 1);
        prefix = TextUtil.normalizeToken(prefix);

        System.out.println("Matched terms:");

        for (String term : index.getVocabulary()) {
            if (term.startsWith(prefix)) {
                System.out.println("- " + term);
                result.addAll(index.getPostingList(term));
            }
        }

        return result;
    }
}