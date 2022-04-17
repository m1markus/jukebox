package ch.m1m.jukebox;

import ch.m1m.jukebox.model.db.Song;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/v1/song")
public class SongResource {

    @Inject
    SongService songService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Song> getAllSongList() {
        return songService.getSongList();
        //return songService.getDummySongList();
    }
}