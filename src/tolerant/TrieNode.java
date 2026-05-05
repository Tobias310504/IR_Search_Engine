package tolerant;

import java.util.HashMap;
import java.util.Map;
//kelas untuk membuat kerangka trie
public class TrieNode {
    //dipakai untuk menyimpan cabang karakter berikutnya
    private Map<Character, TrieNode> children;
    //menandakan apakah character itu adalah merupakan akhir dari sebuat term atau bukan
    private boolean endOfWord;
    //untuk menyimpan kata lengkap pada node terakhir
    private String term;
    //Konstruktur kelas TrieNode
    public TrieNode(){
        this.children = new HashMap<>();
        this.endOfWord = false;
        this.term = null;
    }
    // getter dan setter
    public Map<Character, TrieNode> getChildren(){
        return this.children;
    }

    public void setEndOfWord(boolean endOfWord){
        this.endOfWord = endOfWord;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public boolean getEndOfWord(){
        return this.endOfWord;
    }
}