package sportify_backend.entite.image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {
    @Value("./src/main/resources/")
    private String imgPath;

    public Resource getImgAsResource(String imgFileName) throws MalformedURLException, FileNotFoundException {
            //create instance
            Path dirPath = Paths.get(imgPath);
            Path file = dirPath.resolve(imgFileName);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists()){
                return resource;
            }
            throw new FileNotFoundException("Resource was not founded");
    }
    public void storeImg(MultipartFile img , String fileName) {
        try{
            byte[] bytes = img.getBytes();
            Path path = Paths.get( imgPath +'/'+ fileName);
            Files.write(path, bytes);
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    public void deleteImage(String imgFileName) throws IOException {
        Path dirPath = Paths.get(imgPath);
        Path file = dirPath.resolve(imgFileName);
        Files.deleteIfExists(file);
    }

}
