package util;

public class TextUtil {

    public static String normalizeText(String text) {
        if (text == null) return "";

        return text
                .toLowerCase()
                .replaceAll("[^a-z0-9\\s]", " ")
                .replaceAll("\\s+", " ")
                .trim();
    }

    public static String normalizeToken(String token) {
        if (token == null) {
            return "";
        }
        token = token.toLowerCase();
        token = token.replaceAll("[^a-z0-9]", "");

        return simpleStem(token);
    }

    public static String simpleStem(String token) {
        if (token.endsWith("ing") && token.length() > 5) {
            return token.substring(0, token.length() - 3);
        }

        if (token.endsWith("ed") && token.length() > 4) {
            return token.substring(0, token.length() - 2);
        }

        if (token.endsWith("s") && token.length() > 3) {
            return token.substring(0, token.length() - 1);
        }

        return token;
    }

    public static boolean isBooleanOperator(String token) {
        return token.equalsIgnoreCase("AND")
                || token.equalsIgnoreCase("OR")
                || token.equalsIgnoreCase("NOT");
    }

    public static boolean containsBooleanOperator(String query) {
        String upper = query.toUpperCase();
        return upper.contains(" AND ")
                || upper.contains(" OR ")
                || upper.contains(" NOT ")
                || query.contains("(")
                || query.contains(")");
    }

    public static boolean containsWildcard(String query) {
        return query != null && query.contains("*");
    }
}