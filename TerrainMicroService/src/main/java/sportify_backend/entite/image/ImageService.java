package sportify_backend.entite.image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {


    public Resource getImgAsResource(String imgFileName) throws MalformedURLException, FileNotFoundException {
            //create instance
        Path targetPath = null;
        try {
            targetPath = Paths.get(getClass().getResource("/").toURI()).getParent();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        Path file = targetPath.resolve(imgFileName);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists()){
                return resource;
            }
            throw new FileNotFoundException("Resource was not founded");
    }
    public void storeImg(MultipartFile img , String fileName) {
        try{
            byte[] bytes = img.getBytes();
            Path targetPath = Paths.get(getClass().getResource("/").toURI()).getParent();
            String paths = targetPath.toString();
            Path path = Paths.get( paths +'/'+ fileName);
            if(Files.notExists(path)) {
                Files.write(path,bytes);
            }else {
                throw new RuntimeException("Image name already exists please change it : ");
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    public void deleteImage(String imgFileName) throws IOException {
        Path targetPath = null;
        try {
            targetPath = Paths.get(getClass().getResource("/").toURI()).getParent();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        Path file = targetPath.resolve(imgFileName);
        Files.deleteIfExists(file);
    }

}
