package ch.m1m.jukebox;

import ch.m1m.jukebox.model.db.Song;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.agroal.api.AgroalDataSource;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

@ApplicationScoped
public class FileSongService {

    public static String SONGS_FILE_NAME_JSON = "songs.json";

    @ConfigProperty(name = "jukebox.upload.directory")
    String UPLOAD_DIR;

    private static final Logger LOG = Logger.getLogger(FileSongService.class);

    public List<Song> getAll() throws IOException {
        LOG.info("called getSongList()");
        List<Song> songList = readAllFrom();
        LOG.info("return song list: " + songList.toString());
        return songList;
    }

    public void insert(Song song) {
        LOG.info("try to insert new song: " + song.toString());

        //Gson gson = new Gson();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        //gson.toJson(user, new FileWriter(filePath));
    }

    public List<Song> getDummySongList() {
        List<Song> songList = new ArrayList<Song>();
        songList.add(new Song("123", "MC Markus", "Everything I can say"));
        songList.add(new Song("1234", "MC Sandra", "Everything I can see"));
        LOG.info("return dummy song list: " + songList.toString());
        return songList;
    }

    private List<Song> readAllFrom() throws IOException {
        Gson gson = new Gson();
        String filePath = UPLOAD_DIR + File.separator + SONGS_FILE_NAME_JSON;
        Reader reader = Files.newBufferedReader(Paths.get(filePath));

        List<Song> songList = new Gson().fromJson(reader, new TypeToken<List<Song>>() {}.getType());
        reader.close();

        return songList;
    }

    private void createSymbolicLink() throws IOException {
        Path target = createTextFile();
        Path link = Paths.get(".","symbolic_link.txt");
        if (Files.exists(link)) {
            Files.delete(link);
        }
        Files.createSymbolicLink(link, target);
    }

    private Path createTextFile() throws IOException {
        byte[] content = IntStream.range(0, 10000)
                .mapToObj(i -> i + System.lineSeparator())
                .reduce("", String::concat)
                .getBytes(StandardCharsets.UTF_8);
        Path filePath = Paths.get("", "target_link.txt");
        Files.write(filePath, content, CREATE, TRUNCATE_EXISTING);
        return filePath;
    }

    private void printLinkFiles(Path path) throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path file : stream) {
                if (Files.isDirectory(file)) {
                    printLinkFiles(file);
                } else if (Files.isSymbolicLink(file)) {
                    System.out.format("File link '%s' with target '%s' %n",
                            file, Files.readSymbolicLink(file));
                }
            }
        }
    }
}
