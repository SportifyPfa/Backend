package sportify_backend.entite.image;


import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/terrain/images")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(value = "/{imgFileName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> loadImage(@PathVariable("imgFileName") String imgFileName) throws IOException {
        Resource resource =imageService.getImgAsResource(imgFileName);
        return ResponseEntity
                .ok()
                .header(MediaType.IMAGE_JPEG_VALUE, "attachment; filename=\""+resource.getFilename()+"\"")
                .body(resource);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveImage(@RequestPart MultipartFile img) throws IOException {
        String imgFileName = img.getOriginalFilename();
        imageService.storeImg(img, imgFileName);
        return new ResponseEntity<>(imgFileName, HttpStatus.OK);
    }
}
