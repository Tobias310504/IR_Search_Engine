import core.Document;
import core.DocumentStore;
import core.InvertedIndex;
//import core.SearchEngine;
import core.SearchEngine;
import preprocessing.BasicPreprocessor;
import tolerant.EditDistanceStrategy;
import tolerant.TolerantRetrieval;
import tolerant.Trie;
import tolerant.WildcardSearchStrategy;
import util.TextUtil;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        //inisialisasi scanner
        Scanner scanner = new Scanner(System.in);
       //inisialisasi search engine
        SearchEngine engine = new SearchEngine(new BasicPreprocessor());
        //membaca dokumen yang ada, sebelum user memasukan query
        engine.buildIndex("data/dokumen.txt");
        //print total dokumen yang ada dan print total terms yang ada
        System.out.println("Index built successfully.");
        System.out.println("Total documents: " + engine.getDocumentCount());
        System.out.println("Total unique terms: " + engine.getVocabularySize());
        //membuat progamt jalan sebelum user memilih exit
        while (true) {
            System.out.println("\n===== SIMPLE IR SEARCH ENGINE =====");
            System.out.println("1. Search query");
            System.out.println("2. Show sample inverted index");
            System.out.println("3. Exit");
            System.out.print("Choose menu: ");
            //untuk memilih dari 3 pilihan di terminal
            String choice = scanner.nextLine();
            //kalau pilih menu 1
            if (choice.equals("1")) {
                //user diminta memasukan query
                System.out.print("Enter query: ");
                String query = scanner.nextLine();
                //akan mencari docId yang ada di set result
                Set<Integer> results = engine.search(query);

                System.out.println("\nSearch results for: " + query);
                //kalau query tidak ditemukan maka akan mengeluarkan term suggestion
                if (engine.getLastSuggestion() != null) {
                    System.out.println("Did you mean: " + engine.getLastSuggestion());
                }
                // kalau ada akan mengeluarkan
                engine.printResults(results);
            //kalau memilih 2 maka program akan masuk ke menu 2
            } else if (choice.equals("2")) {
                System.out.println("\nSample inverted index:");
                //memprint seluruh inverted index banyak printnya sesuai limit yang diberikan
                engine.printSampleIndex(30);
            //kalau memilih 3 makan user akan masuk ke menu 3 yaitu exit
            } else if (choice.equals("3")) {
                System.out.println("Exiting program...");
                //program berakhir dengan break
                break;

            } else {
                //kalau memilih di luar 1, 2, atau 3 maka akan memprint "Invalid menu choice" dan akan kembali ke dashboard awak
                System.out.println("Invalid menu choice.");
            }
        }

        scanner.close();
    }
}