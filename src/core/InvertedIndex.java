package core;

import java.util.*;

public class InvertedIndex {
    //atribut untuk invertedIndex, posting list dibuat dengan menggunakan hashmap <String (untuk term),Set<Integer> (untuk docIdnya)>
    private Map<String, Set<Integer>> index;
    //konstruktor untuk kelas Inverted Index
    public InvertedIndex() {
        this.index = new HashMap<>();
    }

    public void addDocument(int docId, List<String> tokens) {
        // Untuk setiap token:
        for (String token : tokens) {
            // kalau token belum ada
            if(!index.containsKey(token)) {
                //buat term baru untuk posting list
                index.put(token, new TreeSet<Integer>());
            }
            // mengambil postinglist berdasarkan term yaitu token lalu memasukan docId ke dalam hashmap untuk membuat posting list
            index.get(token).add(docId);
        }
    }
    // method untuk mengembalikan daftar doc_id untuk term tertentu, kalau term belum ada maka akan mengembalikan treeset (postinglist) dengan term baru
    public Set<Integer> getPostingList(String term) {
        //kalau term belum ada di kumpulan posting list
        if(!index.containsKey(term)) {
            //buat postinglist baru dengan term yang baru
            return new TreeSet<>(index.get(term));
        }
        //kembalikan postinglist berdasarkan termnya
        return index.get(term);
    }
    // method untuk cek apakah term ada di index, true kalau term sudah ada, false kalau term belum ada
    public boolean containsTerm(String term) {
        return index.containsKey(term);
    }
    // method untuk mengembalikan semua term yang ada di index
    public Set<String> getVocabulary() {
      return  index.keySet();
    }
    //mengembalikan jumlah term
    public int getVocabularySize() {
        return index.size();
    }
    // method untuk ngeprint inverted index
    public void printSampleIndex(int limit) {
        int count = 0;
        //loop untuk setiap postinglist
        for(Map.Entry<String, Set<Integer>> entry : index.entrySet()) {
            //kalau count belum lebih besar dari limit print posting list
            System.out.println(entry.getKey() + "->" + entry.getValue());
            count++;
            //kalau count lebih besar daripada limit maka print berhenti
            if(count > limit){
                break;
            }
        }
    }
}