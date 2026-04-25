package booleanquery;

import core.InvertedIndex;
import java.util.Set;

public class BooleanEngine {
    private InvertedIndex index;
    private QueryParser parser;

    public BooleanEngine(InvertedIndex index) {
        this.index = index;
        this.parser = new QueryParser();
    }

    public Set<Integer> search(String query, Set<Integer> allDocumentIds) {
        QueryExpression expression = parser.parse(query);
        return expression.evaluate(index, allDocumentIds);
    }
}