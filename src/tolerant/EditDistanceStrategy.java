package tolerant;

import core.InvertedIndex;
import util.TextUtil;
import java.util.*;

public class EditDistanceStrategy implements TolerantSearchStrategy {

    private int maxDistance = 2;

    @Override
    public Set<Integer> search(String query, InvertedIndex index) {
        String suggestion = suggest(query, index);

        if (suggestion == null) {
            System.out.println("No suitable correction found.");
            return new HashSet<>();
        }

        System.out.println("Did you mean: " + suggestion + "?");
        return index.getPostingList(suggestion);
    }

    public String suggest(String query, InvertedIndex index) {
        String normalizedQuery = TextUtil.normalizeToken(query);

        String bestTerm = null;
        int bestDistance = Integer.MAX_VALUE;

        for (String term : index.getVocabulary()) {
            int distance = levenshteinDistance(normalizedQuery, term);

            if (distance < bestDistance) {
                bestDistance = distance;
                bestTerm = term;
            }
        }

        if (bestDistance <= maxDistance) {
            return bestTerm;
        }

        return null;
    }

    private int levenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= b.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                int cost = a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1;

                dp[i][j] = Math.min(
                        Math.min(
                                dp[i - 1][j] + 1,
                                dp[i][j - 1] + 1
                        ),
                        dp[i - 1][j - 1] + cost
                );
            }
        }

        return dp[a.length()][b.length()];
    }
}