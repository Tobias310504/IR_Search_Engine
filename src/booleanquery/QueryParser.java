package booleanquery;

import util.TextUtil;
import java.util.*;

public class QueryParser {

    public QueryExpression parse(String query) {
        List<String> tokens = tokenize(query);
        List<String> fixedTokens = insertImplicitAndBeforeNot(tokens);
        List<String> postfix = toPostfix(fixedTokens);

        return buildExpressionTree(postfix);
    }

    private List<String> tokenize(String query) {
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
    }

    private List<String> insertImplicitAndBeforeNot(List<String> tokens) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < tokens.size(); i++) {
            String current = tokens.get(i);

            if (current.equalsIgnoreCase("NOT") && i > 0) {
                String previous = tokens.get(i - 1);

                boolean previousIsOperand = !TextUtil.isBooleanOperator(previous)
                        && !previous.equals("(");

                if (previousIsOperand || previous.equals(")")) {
                    result.add("AND");
                }
            }

            result.add(current);
        }

        return result;
    }

    private int precedence(String operator) {
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
    }

    private List<String> toPostfix(List<String> tokens) {
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
    }

    private QueryExpression buildExpressionTree(List<String> postfix) {
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
    }
}