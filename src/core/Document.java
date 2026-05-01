package core;
//kelas untuk menyimpan id, title, dan content dari 1 dokumen id
public class Document {
    private int id;
    private String title;
    private String content;

    //konstruktur dari dokumen
    public Document(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}