package booleanquery;

import core.InvertedIndex;
import java.util.Set;

public class TermExpression implements QueryExpression {
    private String term;

    public TermExpression(String term) {
        this.term = term;
    }

    @Override
    public Set<Integer> evaluate(InvertedIndex index, Set<Integer> allDocumentIds) {
        return index.getPostingList(term);
    }
}