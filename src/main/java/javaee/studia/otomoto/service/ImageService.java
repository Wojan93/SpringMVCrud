package javaee.studia.otomoto.service;

import javaee.studia.otomoto.commands.ImageCommand;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

        ImageCommand findByCarIdAndImageId(Long carId, Long imageId);

        ImageCommand saveImageCommand(ImageCommand command);

        void saveImageFile(Long carId, Long imageId, String imageFilename, String imageType, MultipartFile file);

        void deleteById(Long carId, Long idToDelete);
}
