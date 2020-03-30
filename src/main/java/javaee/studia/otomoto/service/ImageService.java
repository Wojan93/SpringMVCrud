package javaee.studia.otomoto.service;

import org.springframework.web.multipart.MultipartFile;


public interface ImageService {

        void saveImageFileCar(Long id, MultipartFile file);
        void saveImageFileMoto(Long id, MultipartFile file);
}
