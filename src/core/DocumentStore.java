package core;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class DocumentStore {
    private Map<Integer, Document> documents = new HashMap<>();

    public void loadFromFile(String filePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            for (String line : lines) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split("\\|", 3);

                if (parts.length < 3) {
                    System.out.println("Invalid document format: " + line);
                    continue;
                }

                int id = Integer.parseInt(parts[0].trim());
                String title = parts[1].trim();
                String content = parts[2].trim();

                Document document = new Document(id, title, content);
                documents.put(id, document);
            }

        } catch (IOException e) {
            System.out.println("Error loading documents: " + e.getMessage());
        }
    }

    public Document getDocumentById(int id) {
        return documents.get(id);
    }

    public Collection<Document> getAllDocuments() {
        return documents.values();
    }

    public Set<Integer> getAllDocumentIds() {
        return documents.keySet();
    }

    public int size() {
        return documents.size();
    }
}