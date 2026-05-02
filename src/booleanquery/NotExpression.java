package booleanquery;

import core.InvertedIndex;
import java.util.Set;

public class NotExpression implements QueryExpression {
    private QueryExpression expression;

    public NotExpression(QueryExpression expression) {
        this.expression = expression;
    }

    @Override
    public Set<Integer> evaluate(InvertedIndex index, Set<Integer> allDocumentIds) {
        // TODO:
        // 1. Ambil semua document id
        // 2. Evaluasi expression
        // 3. Hapus hasil expression dari semua document id
        throw new UnsupportedOperationException("TODO");
    }
}