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
        //reset suggestion ke null
        this.lastSuggestion = null;
        //kalau querynya nya null atau kosong return result kosong
        if(query == null || query.trim().isEmpty()) {
            return result;
        }
        //kalau indexnya null return result kosong
        if(index==null) {
            return result;
        }
        //query nya normalisasi dulu
        query = TextUtil.normalizeToken(query);
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
        // kalau query null atau kosong, tidak ada suggestion
        if (query == null || query.isEmpty()) {
            return null;
        }

        // kalau index null, tidak bisa mencari vocabulary
        if (index == null) {
            return null;
        }

        // normalisasi query supaya sama dengan bentuk term di index
        query = TextUtil.normalizeToken(query);

        // kalau setelah normalisasi query kosong, return null
        if (query.isEmpty()) {
            return null;
        }

        // menyimpan term terbaik sementara
        String bestTerm = null;

        // menyimpan jarak edit terkecil sementara
        int bestDistance = Integer.MAX_VALUE;

        // bandingkan query dengan semua term di vocabulary
        for (String term : index.getVocabulary()) {
            int distance = levenshteinDistance(query, term);

            // kalau distance lebih kecil dari bestDistance,
            // update bestDistance dan bestTerm
            if (distance < bestDistance) {
                bestDistance = distance;
                bestTerm = term;
            }
        }

        // kalau jarak edit masih dalam batas toleransi,
        // return term terbaik sebagai suggestion
        if (bestDistance <= maxDistance) {
            return bestTerm;
        }

        // kalau terlalu jauh, tidak ada suggestion
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

    //method untuk mengreset suggstion jadi null
    public void clearLastSuggestion(){
        //set lastSuggestion jadi null
        this.lastSuggestion = null;
    }
}