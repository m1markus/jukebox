package ch.m1m.jukebox;

import ch.m1m.jukebox.model.db.Song;

import javax.inject.Inject;
import javax.ws.rs.*;
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

    /*
curl -X POST http://localhost:8080/api/v1/song/add \
   -H 'Content-Type: application/json' \
   -d '{"id":"2","interpret":"mc marc","title":"jost do it"}'
*/
    @Path("add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Song addSong(Song song) {
        songService.insert(song);
        return song;
    }
}
