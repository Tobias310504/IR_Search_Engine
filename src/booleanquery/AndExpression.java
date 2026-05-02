package booleanquery;

import core.InvertedIndex;

import java.util.HashSet;
import java.util.Set;

public class AndExpression implements QueryExpression {
    private QueryExpression left;
    private QueryExpression right;

    public AndExpression(QueryExpression left, QueryExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Set<Integer> evaluate(InvertedIndex index, Set<Integer> allDocumentIds) {
        // 1. Evaluasi left
        Set<Integer> result = new HashSet<>(left.evaluate(index, allDocumentIds));
        // 2. Evaluasi right
        result.retainAll(right.evaluate(index, allDocumentIds));
        // 3. Lakukan intersection
        //throw new UnsupportedOperationException("TODO");
        return result;
    }
}