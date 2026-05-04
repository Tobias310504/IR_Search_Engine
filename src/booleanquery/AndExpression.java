package booleanquery;

import core.InvertedIndex;
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
        // TODO:
        // 1. Evaluasi left
        // 2. Evaluasi right
        // 3. Lakukan intersection
        Set<Integer> leftResult = left.evaluate(index, allDocumentIds);
        Set<Integer> rightResult = right.evaluate(index, allDocumentIds);
        leftResult.retainAll(rightResult);
        return leftResult;
    }
}