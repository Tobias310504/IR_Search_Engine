package booleanquery;

import core.InvertedIndex;
import java.util.*;

public class AndExpression implements QueryExpression {
    private QueryExpression left;
    private QueryExpression right;

    public AndExpression(QueryExpression left, QueryExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Set<Integer> evaluate(InvertedIndex index, Set<Integer> allDocumentIds) {
        Set<Integer> result = new HashSet<>(left.evaluate(index, allDocumentIds));
        result.retainAll(right.evaluate(index, allDocumentIds));
        return result;
    }
}