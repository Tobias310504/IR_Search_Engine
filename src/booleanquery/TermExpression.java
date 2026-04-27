package booleanquery;

import core.InvertedIndex;
import java.util.Set;

public class TermExpression implements QueryExpression {
    private String term;

    public TermExpression(String term) {
        // TODO: simpan term
    }

    @Override
    public Set<Integer> evaluate(InvertedIndex index, Set<Integer> allDocumentIds) {
        // TODO:
        // Return posting list dari term
        throw new UnsupportedOperationException("TODO");
    }
}