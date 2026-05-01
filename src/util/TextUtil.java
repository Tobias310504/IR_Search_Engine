package util;

public class TextUtil {
    //menormalisasi 1 kalimat jadi masih akan ada spasi
    public static String normalizeText(String text) {
        //kalau text null return string kosong
        if(text==null) {
            return "";
        }
        //mengubah text ke lowercase
        text = text.toLowerCase();
        //Hapus tanda baca dang menggantinya dengan spasi
        text = text.replace("[^a-z0-9\\s]"," ");
        //Merapihkan spasi yang berlebih menjadi satu spasi
        text = text.replace("\\s+", " ");
        //Rapikan spasi diawal dan akhir
        text = text.trim();
        return text;
    }
    //tidak akan ada spasi karena token itu berupa kata
    public static String normalizeToken(String token) {
        // kalau token null, return string kosong
        if(token==null) {
            return "";
        }
        // mengubah semua huruf ditoken ke lowercase
        token = token.toLowerCase();
        // hapus karakter selain huruf dan angka
        token = token.replaceAll("[^a-z0-9]", "");
        // lakukan simpleStem
        token = simpleStem(token);
        return token;
    }
    // Buat stemming untuk menghapus akhiran jika token yang dihasilkan terlalu panjang
    public static String simpleStem(String token) {
        //kalau token null atau panjang katanya lebih besar dari 4 maka kembalikan si tokennya
        if(token==null || token.length()<=3) {
            return token;
        }
        // hapus token dengan akhiran "ing"
        if(token.endsWith("ing") && token.length()>5) {
            return token.substring(0, token.length()-3);
        }
        // hapus token dengan akhiran "ed"
        if(token.endsWith("ed") && token.length()>4) {
            return token.substring(0, token.length()-2);
        }
        // hapus token dengan akhiran "s"
        if(token.endsWith("es") && token.length()>4) {
            return token.substring(0, token.length()-2);
        }
        if(token.endsWith("s") && token.length()>3 && !token.endsWith("ss") && !token.endsWith("us") && !token.endsWith("is")) {
            return token.substring(0, token.length()-1);
        }
        return token;
    }

    public static boolean isBooleanOperator(String token) {
        // TODO:
        // Cek apakah token adalah AND, OR, atau NOT
        throw new UnsupportedOperationException("TODO");
    }

    public static boolean containsBooleanOperator(String query) {
        // TODO:
        // Cek apakah query mengandung AND, OR, NOT, atau tanda kurung
        throw new UnsupportedOperationException("TODO");
    }

    public static boolean containsWildcard(String query) {
        // TODO:
        // Cek apakah query mengandung "*"
        throw new UnsupportedOperationException("TODO");
    }
}