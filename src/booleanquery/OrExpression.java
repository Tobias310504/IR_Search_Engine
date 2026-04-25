package booleanquery;

import core.InvertedIndex;
import java.util.*;

public class OrExpression implements QueryExpression {
    private QueryExpression left;
    private QueryExpression right;

    public OrExpression(QueryExpression left, QueryExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Set<Integer> evaluate(InvertedIndex index, Set<Integer> allDocumentIds) {
        Set<Integer> result = new HashSet<>(left.evaluate(index, allDocumentIds));
        result.addAll(right.evaluate(index, allDocumentIds));
        return result;
    }
}