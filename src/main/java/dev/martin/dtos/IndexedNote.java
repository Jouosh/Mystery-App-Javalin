package dev.martin.dtos;

public class IndexedNote {
    int index;
    String note;

    public IndexedNote() {
    }

    public IndexedNote(int index, String note) {
        this.index = index;
        this.note = note;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "IndexedNote{" +
                "index=" + index +
                ", note='" + note + '\'' +
                '}';
    }
}
