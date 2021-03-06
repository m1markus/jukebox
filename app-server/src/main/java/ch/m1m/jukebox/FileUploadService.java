package ch.m1m.jukebox;

import org.apache.commons.io.IOUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.inject.Singleton;
import javax.ws.rs.core.MultivaluedMap;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;

@Singleton
public class FileUploadService {

    private static final Logger LOG = Logger.getLogger("FileUploadService");

    @ConfigProperty(name = "jukebox.upload.directory")
    String UPLOAD_DIR;

    public FileUploadService() {
    }

    public String uploadFile(MultipartFormDataInput input) {
        LOG.info("uploadFile() upload directory is: " + UPLOAD_DIR);
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get("file");
        String fileName = null;
        for (InputPart inputPart : inputParts) {
            try {
                MultivaluedMap<String, String> header =
                        inputPart.getHeaders();
                fileName = getFileName(header);
                fileName = FileSongService.SONGS_FILE_NAME_JSON;
                InputStream inputStream = inputPart.getBody(InputStream.class, null);
                writeFile(inputStream, fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "Files Successfully Uploaded";
    }

    private void writeFile(InputStream inputStream, String fileName) throws IOException {
        byte[] bytes = IOUtils.toByteArray(inputStream);
        File customDir = new File(UPLOAD_DIR);
        fileName = customDir.getAbsolutePath() + File.separator + fileName;
        Files.write(Paths.get(fileName), bytes,
                StandardOpenOption.CREATE_NEW);
    }

    private String getFileName(MultivaluedMap<String, String> header) {
        String[] contentDisposition = header.
                getFirst("Content-Disposition").split(";");
        for (String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {
                String[] name = filename.split("=");
                String finalFileName = name[1].trim().replaceAll("\"", "");
                return finalFileName;
            }
        }
        return "";
    }
}