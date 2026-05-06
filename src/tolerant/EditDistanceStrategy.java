package tolerant;

import core.InvertedIndex;
import preprocessing.BasicPreprocessor;
import preprocessing.Preprocessor;
import util.TextUtil;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class EditDistanceStrategy implements TolerantSearchStrategy {
    private int maxDistance;
    private String lastSuggestion;

    public EditDistanceStrategy() {
        this.maxDistance = 2;
    }
    //method untuk mencari posting list yang jarak edit nya terdekat antara query yang dituliskan dengan term yang ada di kumpulan dokumen yang ada
    @Override
    public Set<Integer> search(String query, InvertedIndex index) {
        //inisialisasi result kosong
        Set<Integer> result = new TreeSet<>();
        //kalau querynya nya null atau kosong return result kosong
        if(query==null || query.length()==0) {
            return result;
        }
        //kalau indexnya null return result kosong
        if(index==null) {
            return result;
        }
        //query nya normalisasi dulu
        query = TextUtil.normalizeText(query);
        //kalau querynya kosong return result kosong
        if(query.isEmpty()){
            return result;
        }
        //cari suggestion yaitu yang jarak editnya paling dekat
        String suggestion = suggest(query, index);
        //cari suggestionnya, kalau tidak ada return result kosong
        if(suggestion == null){
            return result;
        }
        //simpan suggestion yang diberikan
        this.lastSuggestion = suggestion;
        //ambil posting list dari term hasil suggestion
        result.addAll(index.getPostingList(suggestion));
        //return result yang berisikan docId dari hasil suggestion
        return result;
    }

    public String suggest(String query, InvertedIndex index) {
        //kalau query null atau kosong return null
        if(query==null || query.length()==0) {
            return null;
        }
        //kalau di indexnya null return null
        if(index==null) {
            return null;
        }
        //normalisasi querynya terlebih dahulu agar sama dengan term" yg ada di posting list
        query = TextUtil.normalizeText(query);
        //inisialisasi term yang paling dekat dulu dan isi dengan null
        String bestTerm = null;
        //inisialisasi jarak terdekat dan berikan nilai yang paling besar karna yang akan diambil adalah jarak yang paling dekat
        int bestDistance = Integer.MAX_VALUE;
        //loop untuk membandingkan query dengan semua term di vocabulary
        for(String term : index.getVocabulary()){
            //cari jarak perubahannya dari apa yang dicari yaitu querynya dan apa yang ada di dokumen yaitu term
            int distance = levenshteinDistance(query, term);
            //ambil yang distancenya paling kecil
            if(distance < bestDistance){
                //best distancenya berubah dengan distance yang lebih kecil
                bestDistance = distance;
                //termnya berubah ke term yang ada di dokumen dan paling mendekati query yang ada
                bestTerm = term;
            }
        }
        //kalau jaraknya masih dalam batas toleransi, kembalikan suggestionnya
        if(bestDistance <= maxDistance){
            return bestTerm;
        }
        return null;
    }

    private int levenshteinDistance(String a, String b) {
        //kalau String a null isi a dengan string kosong
        if(a==null) {
            a = "";
        }
        //kalau string b null isi b dengan string kosong
        if(b==null) {
            b = "";
        }
        //inisialisasi array 2D untuk melakukan perhitungan dynamic programming
        int[][] dp = new int[a.length()+1][b.length()+1];
        //loop untuk inisialisasi kolom pertama
        for(int i = 0;i <= a.length() ;i++) {
            dp[i][0] = i;
        }
        //inisialisasi baris pertama
        for(int i = 0;i <= b.length() ;i++) {
            dp[0][i] = i;
        }
        //double loop untuk mengisi tabel dynamic programming
        for(int i = 1;i <= a.length();i++) {
            for(int j = 1;j <= b.length();j++) {
                //buat varriable baru untuk menyimpan cost yang dibutuhkan berapa
                int cost;
                //kalau String A = String B maka tidak perlu ada perubahan jadi costnya 0
                if(a.charAt(i-1) == b.charAt(j-1)) {
                    cost = 0;
                    //kalau ada perubahan maka costnya 1
                }else  {
                    cost = 1;
                }
                //hitung untuk menghapus huruf
                int delete = dp[i-1][j] + 1;
                //hitung untuk memasukan huruf baru
                int insert = dp[i][j-1] + 1;
                //hitung kalau harus mengganti huruf
                int replace = dp[i-1][j-1] + cost;
                //hitung hasil dari dpnya pakai rumus cari yang nilai nya paling sedikit
                dp[i][j] = Math.min(Math.min(delete, insert), replace);
            }
        }
        //kembalikan hasilnya
        return dp[a.length()][b.length()];
    }
    public String getLastSuggestion(){
        return lastSuggestion;
    }
}