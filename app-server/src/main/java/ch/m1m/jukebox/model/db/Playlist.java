package ch.m1m.jukebox.model.db;

import java.util.List;

public class Playlist {

    private Long version;
    private List<Song> songList;

    public Playlist() {
    }

    public Playlist(Long version, List<Song> songList) {
        this.version = version;
        this.songList = songList;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "version=" + version +
                ", songList=" + songList +
                '}';
    }
}
