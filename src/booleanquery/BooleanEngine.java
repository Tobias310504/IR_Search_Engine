package booleanquery;

import core.InvertedIndex;
import java.util.Set;

public class BooleanEngine {
    private InvertedIndex index;
    private QueryParser parser;

    public BooleanEngine(InvertedIndex index) {
        // TODO:
        // Simpan index
        // Inisialisasi parser
        this.index = index;
        this.parser = new QueryParser();
    }

    public Set<Integer> search(String query, Set<Integer> allDocumentIds) {
        // TODO:
        // 1. Parse query menjadi QueryExpression
        // 2. Evaluate expression
        // 3. Return hasil doc_id
        QueryExpression expression = parser.parse(query);
        return expression.evaluate(index, allDocumentIds);
        //throw new UnsupportedOperationException("TODO");
    }
}