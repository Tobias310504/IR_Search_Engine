package preprocessing;

import util.TextUtil;

import java.util.*;

public class BasicPreprocessor implements Preprocessor {
    private Set<String> stopwords;

    public BasicPreprocessor() {
        // Inisialisasi daftar stopword beserta isi stopwordsnya
        this.stopwords = new HashSet<String>(Arrays.asList(
                "a", "an", "the",
                "is", "are", "was", "were",
                "and", "or",
                "of", "to", "in", "on", "at", "for", "from", "with",
                "as", "by",
                "it", "this", "that"
        ));
    }

    //method untuk mengubah isi artikel yang ada di dokumen mentah menjadi daftar token bersih
    @Override
    public List<String> process(String text) {
        // 1. Normalize text dengan TextUtil
        String cleanText = TextUtil.normalizeText(text);
        //Split menjadi token
        String[] tokens = cleanText.split("\\s+");
        //list untuk menyimpan token yang sudah di normalisasi
        List<String> cleanTokens = new ArrayList<>();
        //Normalisasi token untuk setiap kata yang ada di dalam kalimat
        for(String token : tokens) {
            //menormalisasi token
            String normalizedToken = TextUtil.normalizeToken(token);
            //kalau token kosong maka hapus tokennya
            if(normalizedToken.isEmpty()){
                continue;
            }
            //menghapus token yang berisikan stopwords
            if(stopwords.contains(normalizedToken)){
                continue;
            }
            //memasikan token yang sudah di normalisasi ke dalam list
            cleanTokens.add(normalizedToken);
        }
        //mengembalikan list token yang sudah di normalisasi
        return cleanTokens;


    }
}