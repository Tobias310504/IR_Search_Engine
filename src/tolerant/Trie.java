package tolerant;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    private TrieNode root;
    //konstruktor kelas TrieNode
    public Trie() {
        this.root = new TrieNode();
    }

    //method untuk memasukan 1 term ke dalam trie
    public void insert(String term) {
        //jika term null atau kosong
        if(term == null || term.length() == 0){
            //langsung return, supaya trie tidak menyimpan kata kosong
            return;
        }
        //karena mulai dari root maka character pertama pada term akan ditaruh di root dan akan bergerak kebawah
        TrieNode curr = root;
        //loop sepanjang term yang ada
        for(int i = 0; i < term.length(); i++){
            //ambil character ke - i nya setiap kali loop
            char ch = term.charAt(i);
            //kalau characternya belum ada di trienya
            if(!curr.getChildren().containsKey(ch)){
                //masukan ke dalam trie nya
                curr.getChildren().put(ch, new TrieNode());
            }
            //karena posisi saat ini sudah terisi, maka posisi maju
            curr = curr.getChildren().get(ch);
        }
        //kalau loop sudah selesai berarti kata termnya sudah selesai mangkanya set EndofWord nya jdi true
        curr.setEndOfWord(true);
        //catat juga term fullnya apa
        curr.setTerm(term);
    }
    //method untuk ngecek apakah sebuah term ada di vocabulary(trie), bisa juga buat testing trie
    public boolean search(String term) {
        //kalau term tidak ada
        if(term == null || term.length() == 0){
            //kemabilakn false
            return false;
        }
        //karena mulai dari root maka current yaitu huruf pertama akan dimasukan ke root mangkanya curr = root
        TrieNode curr = root;
        //telusuri term per character
        for(int i = 0; i < term.length(); i++){
            //ambil character ke-i nya
            char ch = term.charAt(i);
            //cek apakah posisi saat ini punya cabang ke karakter ch atau belum
            if(!curr.getChildren().containsKey(ch)) {
                //kalau belum return false
                return false;
            }
                //kalau sudah lanjut ke node berikutnya sesuai karakter ch
                curr = curr.getChildren().get(ch);

        }
        //cek apakah node terakhir adalah akhir kata
        return curr.getEndOfWord();
    }

    public List<String> getTermsWithPrefix(String prefix){
        List<String> result = new ArrayList<>();
        //kalau prefixnya tidak ada return arrayList kosong (result)
        if(prefix == null || prefix.length() == 0){
            return result;
        }
        //mulai dari root jadi curr = root
        TrieNode curr = root;

        //loop untuk setiap karakter prefix
        for(int i = 0; i < prefix.length(); i++){
            //kalau karakter tidak ada children, return result kosong
            if(!curr.getChildren().containsKey(prefix.charAt(i))){
                return result;
            }
            //kalau ada maka curr berpindah ke anaknya
            curr = curr.getChildren().get(prefix.charAt(i));


        }
        //panggil method collectTerms
        collectTerms(curr, result);
        //mengreturn hasil result List<String> yang diolah oleh method collectTerms
        return result;
    }
    //method recursif untuk memasukan term kedalam list
    public void collectTerms(TrieNode node, List<String> result){
        //kalau node null lansung return
        if(node == null){
            return;
        }
        //kalau node adalah akhir kata
        if(node.getEndOfWord()){
            //masukkan node.getTerm ke result
            result.add(node.getTerm());
        }
        //loop semua child yang ada di node
        for(TrieNode child : node.getChildren().values()){
            collectTerms(child, result);
        }

    }

}