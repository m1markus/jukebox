package ch.m1m.jukebox;

import ch.m1m.jukebox.model.db.Song;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/v1/song")
public class SongResource {

    @Inject
    FileSongService songService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Song> getAllSongList() {
        //return songService.getSongList();
        return songService.getDummySongList();
    }

    @Inject
    FileUploadService fileUploadService;

    // from: https://www.knowledgefactory.net/2021/10/quarkus-file-upload-example.html
    //
    // upload with: curl -v -F 'file=@./song.json' http://localhost:8080/api/v1/song/upload
    //
    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public Response fileUpload(@MultipartForm MultipartFormDataInput input) {
        return Response.ok().
                entity(fileUploadService.uploadFile(input)).build();
    }

    @Path("x")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSongListDownload() {
        List<Song> allSongs = songService.getDummySongList();
        return Response.ok(allSongs, MediaType.APPLICATION_JSON_TYPE)
                .header("Content-Disposition", "attachment; filename=\"filename.json\"")
                .build();
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
