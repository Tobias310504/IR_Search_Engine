package booleanquery;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import util.TextUtil;

public class QueryParser {

    public QueryExpression parse(String query) {
        // TODO:
        // 1. Tokenize query
        // 2. Handle tanda kurung
        // 3. Handle prioritas operator NOT, AND, OR
        // 4. Bangun expression tree
        List<String> tokens = tokenize(query);
        //List<String> fixed = insertImplicitAndBeforeNot(tokens);
        //List<String> postfix = toPostfix(fixed);
        //return buildExpressionTree(postfix);
        return null;
    }

    private List<String> tokenize(String query) {
        // TODO:
        // Pisahkan query menjadi token:
        // contoh: information AND retrieval
        // menjadi: ["information", "AND", "retrieval"]
        query = query.replace("(", " ( ");
        query = query.replace(")", " ) ");

        String[] parts = query.trim().split("\\s+");
        List<String> tokens = new ArrayList<>();

        for (String part : parts) {
            if (!part.trim().isEmpty()) {
                tokens.add(part.trim());
            }
        }

        return tokens;
        //throw new UnsupportedOperationException("TODO");
    }

    private int precedence(String operator) {
        // TODO:
        // Tentukan prioritas:
        // NOT paling tinggi
        // AND berikutnya
        // OR paling rendah
        switch (operator.toUpperCase()) {
            case "NOT":
                return 3;
            case "AND":
                return 2;
            case "OR":
                return 1;
            default:
                return 0;
        }
        //throw new UnsupportedOperationException("TODO");
    }

    private List<String> toPostfix(List<String> tokens) {
        // TODO:
        // Ubah infix expression menjadi postfix expression
        List<String> output = new ArrayList<>();
        Stack<String> operators = new Stack<>();

        for (String token : tokens) {
            if (token.equals("(")) {
                operators.push(token);
            } else if (token.equals(")")) {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    output.add(operators.pop());
                }

                if (!operators.isEmpty() && operators.peek().equals("(")) {
                    operators.pop();
                }

            } else if (TextUtil.isBooleanOperator(token)) {
                while (!operators.isEmpty()
                        && !operators.peek().equals("(")
                        && precedence(operators.peek()) >= precedence(token)) {
                    output.add(operators.pop());
                }

                operators.push(token.toUpperCase());

            } else {
                output.add(TextUtil.normalizeToken(token));
            }
        }

        while (!operators.isEmpty()) {
            output.add(operators.pop());
        }

        return output;

        //throw new UnsupportedOperationException("TODO");
    }

    private QueryExpression buildExpressionTree(List<String> postfix) {
        // TODO:
        // Bangun expression tree dari postfix
        Stack<QueryExpression> stack = new Stack<>();

        for (String token : postfix) {
            if (token.equals("AND")) {
                QueryExpression right = stack.pop();
                QueryExpression left = stack.pop();
                stack.push(new AndExpression(left, right));

            } else if (token.equals("OR")) {
                QueryExpression right = stack.pop();
                QueryExpression left = stack.pop();
                stack.push(new OrExpression(left, right));

            } else if (token.equals("NOT")) {
                QueryExpression expression = stack.pop();
                stack.push(new NotExpression(expression));

            } else {
                stack.push(new TermExpression(token));
            }
        }

        if (stack.isEmpty()) {
            throw new IllegalArgumentException("Invalid query");
        }

        return stack.pop();
        //throw new UnsupportedOperationException("TODO");
    }
}