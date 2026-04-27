package util;

public class TextUtil {

    public static String normalizeText(String text) {
        // TODO:
        // 1. Handle jika text null
        // 2. Ubah ke lowercase
        // 3. Hapus tanda baca
        // 4. Rapikan spasi
        throw new UnsupportedOperationException("TODO");
    }

    public static String normalizeToken(String token) {
        // TODO:
        // 1. Handle jika token null
        // 2. Lowercase
        // 3. Hapus karakter non-alfanumerik
        // 4. Panggil simpleStem
        throw new UnsupportedOperationException("TODO");
    }

    public static String simpleStem(String token) {
        // TODO:
        // Buat stemming sederhana, misalnya:
        // - hapus akhiran "ing"
        // - hapus akhiran "ed"
        // - hapus akhiran "s"
        throw new UnsupportedOperationException("TODO");
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