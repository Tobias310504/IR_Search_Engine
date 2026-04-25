package booleanquery;

import core.InvertedIndex;
import java.util.*;

public class NotExpression implements QueryExpression {
    private QueryExpression expression;

    public NotExpression(QueryExpression expression) {
        this.expression = expression;
    }

    @Override
    public Set<Integer> evaluate(InvertedIndex index, Set<Integer> allDocumentIds) {
        Set<Integer> result = new HashSet<>(allDocumentIds);
        result.removeAll(expression.evaluate(index, allDocumentIds));
        return result;
    }
}