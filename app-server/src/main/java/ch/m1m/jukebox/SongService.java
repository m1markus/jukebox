package ch.m1m.jukebox;

import ch.m1m.jukebox.model.db.Song;
import io.agroal.api.AgroalDataSource;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class SongService {

    private static final Logger LOG = Logger.getLogger(SongService.class);

    @Inject
    AgroalDataSource defaultDataSource;

    public List<Song> getSongList() {
        LOG.info("called getSongList()");
        List<Song> songList = readAllFromDB();
        LOG.info("return song list: " + songList.toString());
        return songList;
    }

    public void insert(Song song) {
        LOG.info("try to insert new song: " + song.toString());

        try {
            Connection con = defaultDataSource.getConnection();

            PreparedStatement stmt = con.prepareStatement("INSERT INTO song(id, interpret, title) VALUES(?, ?, ?)");
            stmt.setString(1, song.getId());
            stmt.setString(2, song.getId());
            stmt.setString(3, song.getTitle());

            int numRecs = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Song> getDummySongList() {
        List<Song> songList = new ArrayList<Song>();
        songList.add(new Song("123", "MC Markus", "Everything I can say"));
        songList.add(new Song("1234", "MC Sandra", "Everything I can see"));
        LOG.info("return dummy song list: " + songList.toString());
        return songList;
    }

    private List<Song> readAllFromDB() {
        List<Song> songList = new ArrayList<Song>();

        try {
            Connection con = defaultDataSource.getConnection();

            PreparedStatement stmt = con.prepareStatement("SELECT id, interpret, title FROM song");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Song song = new Song(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3));
                songList.add(song);
                LOG.info("adding song: " + song.toString());
            }
            rs.close();
            stmt.close();

            //throw new RuntimeException("my db error");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return songList;
    }
}
