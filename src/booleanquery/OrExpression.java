package booleanquery;

import core.InvertedIndex;
import java.util.Set;

public class OrExpression implements QueryExpression {
    private QueryExpression left;
    private QueryExpression right;

    public OrExpression(QueryExpression left, QueryExpression right) {
        // TODO: simpan left dan right expression
        this.left = left;
        this.right = right;
    }

    @Override
    public Set<Integer> evaluate(InvertedIndex index, Set<Integer> allDocumentIds) {
        // TODO:
        // 1. Evaluasi left
        // 2. Evaluasi right
        // 3. Lakukan union
        Set<Integer> leftResult = left.evaluate(index, allDocumentIds);
        Set<Integer> rightResult = right.evaluate(index, allDocumentIds);
        leftResult.addAll(rightResult);
        return leftResult;
    }
}