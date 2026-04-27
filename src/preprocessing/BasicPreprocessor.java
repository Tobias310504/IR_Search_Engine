package preprocessing;

import java.util.List;
import java.util.Set;

public class BasicPreprocessor implements Preprocessor {
    private Set<String> stopwords;

    public BasicPreprocessor() {
        // TODO:
        // Inisialisasi daftar stopword
        // Contoh: the, is, are, of, to, in, and, or, etc.
    }

    @Override
    public List<String> process(String text) {
        // TODO:
        // 1. Normalize text dengan TextUtil
        // 2. Split menjadi token
        // 3. Normalize setiap token
        // 4. Hapus token kosong
        // 5. Hapus stopword
        // 6. Return List<String> token bersih
        throw new UnsupportedOperationException("TODO");
    }
}