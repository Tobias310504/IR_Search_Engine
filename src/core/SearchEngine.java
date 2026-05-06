package core;

import preprocessing.Preprocessor;
import booleanquery.BooleanEngine;
import tolerant.TolerantRetrieval;
import util.TextUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SearchEngine {
    private DocumentStore documentStore;
    private Preprocessor preprocessor;
    private InvertedIndex invertedIndex;
    private BooleanEngine booleanEngine;
    private TolerantRetrieval tolerantRetrieval;
    //konstruktor kelas Search engine
    public SearchEngine(Preprocessor preprocessor) {
        this.documentStore = new DocumentStore();
        this.preprocessor = preprocessor;
        this.invertedIndex = new InvertedIndex();
        this.tolerantRetrieval = new TolerantRetrieval();
    }
    //method untuk membaca dokumen dan memproses dokumennya menjadi token yang akan dimasukan ke inverted index untuk dijadikan posting list
    public void buildIndex(String filePath) {
        //load dokumen dari file
        DocumentStore store = new DocumentStore();
        store.loadFromFile("data/dokumen.txt");
        // loop untuk setiap dokumen:
            for (Document document : store.getAllDocuments()){
                //ambil content untuk setiap doc lalu lakukan preprocess untuk setiap dokumen untuk dijadikan token
                List<String> tokens = preprocessor.process(document.getContent());
                //masukkan token ke inverted index
                invertedIndex.addDocument(document.getId(), tokens);
            }
    }

    public Set<Integer> search(String query) {
        //inisialisasi set dengan nama varriabel result
        Set<Integer> result = new TreeSet<>();
        //kalau query null atau kosong return set kosong
        if(query == null || query.trim().isEmpty()){
            return result;
        }
        query = query.trim();
        //Jika query wildcard, pakai tolerant wildcard
        if(TextUtil.containsWildcard(query)){
            return tolerantRetrieval.searchWildcard(query, invertedIndex);
        }
        //Jika query Boolean, pakai BooleanEngine
        if(TextUtil.containsBooleanOperator(query)){
            return booleanEngine.search(query, documentStore.getAllDocumentIds());
        }
        //Jika query single term harus di normalisasikan terlebih dahulu supaya mirip seperti dokumen-dokumen yang sudah di preprocesskan
        String normalizedQuery = TextUtil.normalizeText(query);
        //Jika term tidak ditemukan, pakai edit distance
        if(invertedIndex.containsTerm(normalizedQuery)){
            return invertedIndex.getPostingList(normalizedQuery);
        }
        //kalau query nya ada salah penulisan (typo)
        return tolerantRetrieval.searchWithCorrection(normalizedQuery, invertedIndex);
    }

    public void printResults(Set<Integer> resultIds) {
        //kalau hasil pencarian kosong maka print "No documents found."
        if (resultIds == null || resultIds.isEmpty()) {
            System.out.println("No documents found.");
            //mengembalikan untuk memberhentikan method
            return;
        }
        //loop untuk membaca setiap docId yang ada dari hasil pencarian
        for (Integer id : resultIds) {
            //ambil objek dokumen berdasarkan idnya
            Document doc = documentStore.getDocumentById(id);
            //kalau doc ada maka print idnya, judulnya dan isi dokumennya
            if (doc != null) {
                System.out.println("Doc " + doc.getId() + " - " + doc.getTitle());
                System.out.println(doc.getContent());
                System.out.println();
            }
        }
    }
    //method ini menampilkan isi dari inverted index dengan limit
    public void printSampleIndex(int limit) {
        //memanggil method untuk print inverted index
        invertedIndex.printSampleIndex(limit);
    }
    //method untuk mengambil berapa banyak dokumen yang ada
    public int getDocumentCount() {
        return documentStore.size();
    }
    //method untuk mengambil ada berapa banyak vocabulary yang dihasilkan dari dokumen yang ada
    public int getVocabularySize() {
        return invertedIndex.getVocabularySize();
    }
    //method untuk mengambil suggestion dari kelas tolerant suggestion
    public String getLastSuggestion() {
        return tolerantRetrieval.getLastSuggestion();
    }
}