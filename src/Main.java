import core.SearchEngine;
import preprocessing.BasicPreprocessor;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        SearchEngine searchEngine = new SearchEngine(new BasicPreprocessor());

        String filePath = "data/documents.txt";
        searchEngine.buildIndex(filePath);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== SIMPLE IR SEARCH ENGINE =====");
            System.out.println("1. Search query");
            System.out.println("2. Show sample inverted index");
            System.out.println("3. Exit");
            System.out.print("Choose menu: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                System.out.print("Enter query: ");
                String query = scanner.nextLine();

                Set<Integer> results = searchEngine.search(query);
                searchEngine.printResults(results);

            } else if (choice.equals("2")) {
                searchEngine.printSampleIndex();

            } else if (choice.equals("3")) {
                System.out.println("Program finished.");
                break;

            } else {
                System.out.println("Invalid menu.");
            }
        }

        scanner.close();
    }
}