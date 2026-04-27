package booleanquery;

import core.InvertedIndex;
import java.util.Set;

public class AndExpression implements QueryExpression {
    private QueryExpression left;
    private QueryExpression right;

    public AndExpression(QueryExpression left, QueryExpression right) {
        // TODO: simpan left dan right expression
    }

    @Override
    public Set<Integer> evaluate(InvertedIndex index, Set<Integer> allDocumentIds) {
        // TODO:
        // 1. Evaluasi left
        // 2. Evaluasi right
        // 3. Lakukan intersection
        throw new UnsupportedOperationException("TODO");
    }
}