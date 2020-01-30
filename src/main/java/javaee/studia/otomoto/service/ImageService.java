package javaee.studia.otomoto.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

        void saveImageFile(Long carId, MultipartFile file);
}
