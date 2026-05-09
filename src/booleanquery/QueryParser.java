package booleanquery;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import util.TextUtil;

public class QueryParser {

    public QueryExpression parse(String query) {
        List<String> tokens = tokenize(query);//Query diubah menjadi token
        List<String> fixedTokens = insertImplicitAndBeforeNot(tokens);//Masukkan operator AND sebelum NOT jika tidak ada operator di depannya
        List<String> postfix = toPostfix(fixedTokens);//Ubah infix menjadi postfix

        return buildExpressionTree(postfix);//Bangun expression tree dari postfix

    }

    private List<String> tokenize(String query) {
        query = query.replace("(", " ( ");//Pisahkan tanda kurung dengan spasi agar bisa dipisahkan menjadi token
        query = query.replace(")", " ) ");//Pisahkan tanda kurung dengan spasi agar bisa dipisahkan menjadi token

        String[] parts = query.trim().split("\\s+");//Pisahkan query berdasarkan spasi
        List<String> tokens = new ArrayList<>();//Masukkan token yang sudah dipisahkan ke dalam list

        for(String part : parts) {//Hanya masukkan token yang tidak kosong
            if (!part.trim().isEmpty()) {
                tokens.add(part.trim());
            }
        }

        return tokens;
    }

    private int precedence(String operator) {
        //Prioritas operator= NOT > AND > OR
        switch(operator.toUpperCase()) {
            case "NOT":return 3;
            case "AND":return 2;
            case "OR":return 1;
            default:return 0;
        }
    }

    private List<String> toPostfix(List<String> tokens) {
        List<String> output = new ArrayList<>();//List untuk menyimpan hasil postfix
        Stack<String> operators = new Stack<>();//Stack untuk menyimpan operator sementara

        for(String token : tokens) {//Iterasi setiap token
            if(token.equals("(")) {//Kalau token adalah '(', masukkan ke stack operator
                operators.push(token);
            }
            else if (token.equals(")")) {//Kalau token adalah ')', keluarkan operator dari stack ke output sampai '('
                while(!operators.isEmpty() && !operators.peek().equals("(")) {//Keluarkan operator dari stack ke output sampai '('
                    output.add(operators.pop());
                }

                if(!operators.isEmpty() && operators.peek().equals("(")) {//Keluarkan '(' dari stack
                    operators.pop();
                }

            }
            else if(TextUtil.isBooleanOperator(token)) {//Kalau token adalah operator, keluarkan operator dari stack ke output selama operator di stack punya prioritas >= dengan operator saat ini
                while(!operators.isEmpty() && !operators.peek().equals("(") && precedence(operators.peek()) >= precedence(token)) {
                    output.add(operators.pop());
                }

                operators.push(token.toUpperCase());

            }
            else{//Kalau token adalah operand, masukkan langsung ke output
                output.add(TextUtil.normalizeToken(token));
            }
        }

        while(!operators.isEmpty()) {
            output.add(operators.pop());
        }

        return output;
    }

    private QueryExpression buildExpressionTree(List<String> postfix) {
        Stack<QueryExpression> stack = new Stack<>();//Stack untuk membangun expression tree

        for (String token : postfix) {//Iterasi setiap token di postfix
            if(token.equals("AND")) {//Kalau token = AND, pop dua expression dari stack dan buat AndExpression baru, lalu push kembali ke stack
                QueryExpression right = stack.pop();
                QueryExpression left = stack.pop();
                stack.push(new AndExpression(left, right));

            }
            else if(token.equals("OR")) {//Kalau token = OR, pop dua expression dari stack dan buat OrExpression baru, lalu push kembali ke stack
                QueryExpression right = stack.pop();
                QueryExpression left = stack.pop();
                stack.push(new OrExpression(left, right));

            }
            else if(token.equals("NOT")) {//Kalau token = NOT, pop satu expression dari stack dan buat NotExpression baru, lalu push kembali ke stack
                QueryExpression expression = stack.pop();
                stack.push(new NotExpression(expression));

            } 
            else{//Kalau token adalah operand, buat TermExpression baru dan push ke stack
                stack.push(new TermExpression(token));
            }
        }

        if(stack.isEmpty()) {//Kalau stack kosong, query tidak valid
            throw new IllegalArgumentException("Invalid query");
        }

        return stack.pop();
    }

    private List<String> insertImplicitAndBeforeNot(List<String> tokens) {
        //Untuk kasus seperti "java NOT python" yang seharusnya dibaca sebagai "java AND NOT python"
        List<String> result = new ArrayList<>();//List untuk menyimpan hasil token setelah memasukkan AND implisit

        for(int i = 0; i < tokens.size(); i++) {
            String current = tokens.get(i);

            if(current.equalsIgnoreCase("NOT") && i > 0) {// Cek apakah token saat ini adalah NOT dan bukan token pertama
                String previous = tokens.get(i - 1);

                //Klau token sebelumnya adalah operand (bukan operator dan bukan kurung buka) atau token sebelumnya adalah kurung tutup maka sisipkan AND sebelum NOT
                boolean previousIsOperand = !TextUtil.isBooleanOperator(previous)
                        && !previous.equals("(");

                if (previousIsOperand || previous.equals(")")) {//Sisipkan AND sebelum NOT
                    result.add("AND");
                }
            }

            result.add(current);//Masukkan token saat ini ke hasil
        }

        return result;
    }
}