package javaee.studia.otomoto.service;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Wojciech Polubiec
 * Interfejs do zarządzania zdjęciami, rozdzieliłem implementację od deklaracji metody.
 */


public interface ImageService {

        void saveImageFile(Long carId, MultipartFile file);
}
