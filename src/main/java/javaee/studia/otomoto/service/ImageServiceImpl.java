package javaee.studia.otomoto.service;

import javaee.studia.otomoto.model.CarAdvertisement;
import javaee.studia.otomoto.repository.CarAdRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final CarAdRepository carAdRepository;

    public ImageServiceImpl(CarAdRepository carAdRepository) {
        this.carAdRepository = carAdRepository;
    }


    @Override
    @Transactional
    public void saveImageFile(Long carId, MultipartFile file) {

        try {
            CarAdvertisement carAdvertisement = carAdRepository.findById(carId).get();
            Byte[] byteObjects = new Byte[file.getBytes().length];
            int i = 0;

            for (byte b : file.getBytes()) {
                byteObjects[i++] = b;
            }

            carAdvertisement.setImage(byteObjects);
            carAdRepository.save(carAdvertisement);

        } catch (IOException e) {

           log.error("Error occurred", e);

            e.printStackTrace();
        }
    }



}
