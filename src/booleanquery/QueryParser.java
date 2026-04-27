package booleanquery;

import java.util.List;

public class QueryParser {

    public QueryExpression parse(String query) {
        // TODO:
        // 1. Tokenize query
        // 2. Handle tanda kurung
        // 3. Handle prioritas operator NOT, AND, OR
        // 4. Bangun expression tree
        throw new UnsupportedOperationException("TODO");
    }

    private List<String> tokenize(String query) {
        // TODO:
        // Pisahkan query menjadi token:
        // contoh: information AND retrieval
        // menjadi: ["information", "AND", "retrieval"]
        throw new UnsupportedOperationException("TODO");
    }

    private int precedence(String operator) {
        // TODO:
        // Tentukan prioritas:
        // NOT paling tinggi
        // AND berikutnya
        // OR paling rendah
        throw new UnsupportedOperationException("TODO");
    }

    private List<String> toPostfix(List<String> tokens) {
        // TODO:
        // Ubah infix expression menjadi postfix expression
        throw new UnsupportedOperationException("TODO");
    }

    private QueryExpression buildExpressionTree(List<String> postfix) {
        // TODO:
        // Bangun expression tree dari postfix
        throw new UnsupportedOperationException("TODO");
    }
}