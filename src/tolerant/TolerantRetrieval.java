package tolerant;

import core.InvertedIndex;
import java.util.Set;

public class TolerantRetrieval {
    private WildcardSearchStrategy wildcardStrategy;
    private EditDistanceStrategy editDistanceStrategy;
    //konstruktor kelas TolerantRetrieval
    public TolerantRetrieval() {
        this.wildcardStrategy = new WildcardSearchStrategy();
        this.editDistanceStrategy = new EditDistanceStrategy();
    }

   //method untuk membuat trie untuk wildcardStrategy
    public void buildWildcardTrie(InvertedIndex index) {

        wildcardStrategy.buildTrie(index);
    }
    //method untuk
    public Set<Integer> searchWildcard(String quey, InvertedIndex index) {
        return wildcardStrategy.search(quey, index);
    }
    //method untuk
    public Set<Integer> searchWithCorrection(String quey, InvertedIndex index) {
        return editDistanceStrategy.search(quey, index);
    }
    //method untuk
    public String suggestCorrection(String quey, InvertedIndex index) {
        return editDistanceStrategy.suggest(quey, index);
    }
    //method untuk menampilkan suggestion untuk query mana yang mungkin dimaksud
    public String getLastSuggestion(){
        return editDistanceStrategy.getLastSuggestion();
    }
    //method untuk mengreset suggestion jdi null untuk query selanjutnya
    public void clearLastSuggestion(){
        editDistanceStrategy.clearLastSuggestion();
    }
}