package core;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class DocumentStore {
    private Map<Integer, Document> documents;

    public DocumentStore() {
        // TODO: inisialisasi Map untuk menyimpan dokumen
    }

    public void loadFromFile(String filePath) {
        // TODO:
        // 1. Baca file documents.txt
        // 2. Baca baris per baris
        // 3. Split berdasarkan "|"
        // 4. Ambil doc_id, title, content
        // 5. Buat object Document
        // 6. Masukkan ke Map documents
    }

    public Document getDocumentById(int id) {
        // TODO: ambil Document berdasarkan id
        throw new UnsupportedOperationException("TODO");
    }

    public Collection<Document> getAllDocuments() {
        // TODO: return semua dokumen
        throw new UnsupportedOperationException("TODO");
    }

    public Set<Integer> getAllDocumentIds() {
        // TODO: return semua id dokumen
        throw new UnsupportedOperationException("TODO");
    }

    public int size() {
        // TODO: return jumlah dokumen
        throw new UnsupportedOperationException("TODO");
    }
}