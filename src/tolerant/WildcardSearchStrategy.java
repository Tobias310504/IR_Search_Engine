package tolerant;

import core.InvertedIndex;

import java.util.*;

public class WildcardSearchStrategy implements TolerantSearchStrategy {
    private Trie trie;
    //konstruktor kelas WildcardSearchStrategy
    public WildcardSearchStrategy() {
        this.trie = new Trie();
    }
    //method untuk membuat trie berdasarkan term yang ada  dengan memanggil method insert yang ada di kelas Trie
    public void buildTrie(InvertedIndex index) {
        //loop sampai seluruh vocabulary di InvertedIndex habis
        for(String term : index.getVocabulary()){
            //ambil lalu insert term yang ada di inverted index ke dalam trie
            trie.insert(term);
        }
    }

    @Override
    public Set<Integer> search(String query, InvertedIndex index) {
        //inisialisasi Set<integer> kosong
        TreeSet<Integer> result = new TreeSet<Integer>();
        //kalau query kosong atau query null
        if(query == null || query.trim().isEmpty()) {
            //kembalikan treeset kosong
            return result;
        }
        //kecilkan querynya
        query = query.toLowerCase().trim();
        //cek apakah query punya * di dalam katanya
        if(!query.contains("*")){
            //jika tidak ada maka return result
            return result;
        }
        //cek apakah * ada di akhir query
        if(!query.endsWith("*")){
            //jika tidak ada return result
            return result;
        }
        //ambil prefix sebelum *
        String prefix = query.substring(0, query.length()-1);
        //kalau prefixnya kosong berarti tidak ada kata sebelum * maka return result
        if(prefix.isEmpty()){
            return result;
        }
        //ambil trie yang cocok dengan prefix
        List<String> matchedTerms = trie.getTermsWithPrefix(prefix);
        // loop agar semua setiap term yg mirip dengan atau cocok dengan wildcard, harus ambil semua posting list term itu
        for(String term : matchedTerms){
            //nanti result nya akan berisi docId dari term yg cocok dengan wildcard
            result.addAll(index.getPostingList(term));
        }
        //kembalikan hasil setnya yang berisikan docId
        return result;
    }
}