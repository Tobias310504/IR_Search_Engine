package booleanquery;

import core.InvertedIndex;

import java.util.HashSet;
import java.util.Set;

public class NotExpression implements QueryExpression {
    private QueryExpression expression;

    public NotExpression(QueryExpression expression) {
        this.expression = expression;
    }

    @Override
    public Set<Integer> evaluate(InvertedIndex index, Set<Integer> allDocumentIds) {
        Set<Integer> result = new HashSet<>(allDocumentIds);//Ambil semua document id
        Set<Integer> expressionResult = expression.evaluate(index, allDocumentIds);//Evaluasi expression
        result.removeAll(expressionResult);//Hapus hasil expression dari semua documentid
        return result;
    }
}