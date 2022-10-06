package dev.martin.dtos;

public class NamedDocument {

    String docId;
    String content;

    public NamedDocument() {
    }

    public NamedDocument(String docId, String content) {
        this.docId = docId;
        this.content = content;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "NamedDocument{" +
                "id='" + docId + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
