package booleanquery;

import core.InvertedIndex;
import java.util.Set;

public class OrExpression implements QueryExpression {
    private QueryExpression left;
    private QueryExpression right;

    public OrExpression(QueryExpression left, QueryExpression right) {
        // TODO: simpan left dan right expression
    }

    @Override
    public Set<Integer> evaluate(InvertedIndex index, Set<Integer> allDocumentIds) {
        // TODO:
        // 1. Evaluasi left
        // 2. Evaluasi right
        // 3. Lakukan union
        throw new UnsupportedOperationException("TODO");
    }
}