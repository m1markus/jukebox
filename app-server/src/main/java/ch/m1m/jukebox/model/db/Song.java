package ch.m1m.jukebox.model.db;

public class Song {

    private String id;

    private String interpret;

    private String title;

    public Song() {
    }

    public Song(String id, String interpret, String title) {
        this.id = id;
        this.interpret = interpret;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInterpret() {
        return interpret;
    }

    public void setInterpret(String interpret) {
        this.interpret = interpret;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id='" + id + '\'' +
                ", interpret='" + interpret + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
