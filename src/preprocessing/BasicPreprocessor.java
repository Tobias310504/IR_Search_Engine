package preprocessing;

import util.TextUtil;
import java.util.*;

public class BasicPreprocessor implements Preprocessor {

    private Set<String> stopwords = new HashSet<>(Arrays.asList(
            "a", "an", "the", "is", "are", "was", "were",
            "and", "or", "of", "to", "in", "on", "for",
            "from", "with", "as", "by", "it", "this", "that"
    ));

    @Override
    public List<String> process(String text) {
        String cleanText = TextUtil.normalizeText(text);
        String[] rawTokens = cleanText.split("\\s+");

        List<String> tokens = new ArrayList<>();

        for (String token : rawTokens) {
            String normalized = TextUtil.normalizeToken(token);

            if (normalized.isEmpty()) continue;
            if (stopwords.contains(normalized)) continue;

            tokens.add(normalized);
        }

        return tokens;
    }
}