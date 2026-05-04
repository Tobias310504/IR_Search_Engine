package booleanquery;

import core.InvertedIndex;

import java.util.HashSet;
import java.util.Set;

public class NotExpression implements QueryExpression {
    private QueryExpression expression;

    public NotExpression(QueryExpression expression) {
        // TODO: simpan expression
        this.expression = expression;
    }

    @Override
    public Set<Integer> evaluate(InvertedIndex index, Set<Integer> allDocumentIds) {
        // TODO:
        // 1. Ambil semua document id
        // 2. Evaluasi expression
        // 3. Hapus hasil expression dari semua document id
        Set<Integer> result = new HashSet<>(allDocumentIds);
        Set<Integer> expressionResult = expression.evaluate(index, allDocumentIds);
        result.removeAll(expressionResult);
        return result;
        //throw new UnsupportedOperationException("TODO");
    }
}