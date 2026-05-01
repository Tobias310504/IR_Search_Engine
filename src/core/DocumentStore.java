package core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

//kelas yang menyimpan kumpulan dokumen dalam bentuk hashMap
public class DocumentStore {
    private Map<Integer, Document> documents;

    //konstraktor kelas documenStore
    public DocumentStore() {
        documents = new HashMap<>();
    }
    //method untuk melihat artikel-artikel yang ada di dokumen untuk dicari
    public void loadFromFile(String filePath) {
        try {
            //buat membaca file menggunakan List berupa string
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            //diloop selama masih ada isi di satu baris maka loop akan terus berjalan
            for(String line : lines) {
                //di split berdasarkan garis "|"
                String[] parts = line.split("\\|", 3);
                //kalau ukuran string nya lebih kecil dari 3 berarti format dokumennya salah
                if(parts.length < 3) {
                    System.out.println("Format dokumen salah" + line);
                    continue;
                }

                //ngambil docId nya, dari string diganti ke integer
                int id = Integer.parseInt(parts[0]);
                //ngambil judul filenya
                String title = parts[1];
                //ngambil isi artikelnya
                String content = parts[2];
                //buat objek dokumen (hashMap)
                Document document = new Document(id, title, content);
                //taruh DocId dan documentnya (content, title) ke dalam map
                documents.put(id, document);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //mengambil id dari document
    public Document getDocumentById(int id) {
        return documents.get(id);
    }
    //mengambil semua isi dokumen 1 per 1, (perdokumen)
    public Collection<Document> getAllDocuments() {
        return documents.values();
    }
    //mengambil semua dokumen ID dokumen, .keySet = docId nya jadi mengambil semua docId
    public Set<Integer> getAllDocumentIds() {
        return documents.keySet();
    }
    //mengambil ukurannya, ukuran dinilai dari berapa banyak dokumen yang dapat di muat, kalau ada 100 dokumen berarti DocumenStore.size = 100
    public int size() {
        return documents.size();
    }
}