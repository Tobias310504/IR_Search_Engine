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
        Set<Integer> leftResult = left.evaluate(index, allDocumentIds);//Evaluasi left
        Set<Integer> rightResult = right.evaluate(index, allDocumentIds);//Evaluasi right
        
        leftResult.retainAll(rightResult);//Lakukan intersection
        return leftResult;
    }
}