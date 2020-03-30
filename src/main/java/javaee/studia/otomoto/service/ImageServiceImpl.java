package javaee.studia.otomoto.service;

import javaee.studia.otomoto.model.CarAdvertisement;
import javaee.studia.otomoto.model.MotorcycleAdvertisement;
import javaee.studia.otomoto.repository.CarAdRepository;
import javaee.studia.otomoto.repository.MtAdRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final CarAdRepository carAdRepository;
    private final MtAdRepository mtAdRepository;

    public ImageServiceImpl(CarAdRepository carAdRepository, MtAdRepository mtAdRepository) {
        this.carAdRepository = carAdRepository;
        this.mtAdRepository = mtAdRepository;
    }


    @Override
    @Transactional
    public void saveImageFileCar(Long carId, MultipartFile file) {

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
        @Override
        @Transactional
        public void saveImageFileMoto(Long mtId, MultipartFile file) {

            try {
                MotorcycleAdvertisement motorcycleAdvertisement = mtAdRepository.findById(mtId).get();
                Byte[] byteObjects = new Byte[file.getBytes().length];
                int i = 0;

                for (byte b : file.getBytes()) {
                    byteObjects[i++] = b;
                }

                motorcycleAdvertisement.setImage(byteObjects);
                mtAdRepository.save(motorcycleAdvertisement);

            } catch (IOException e) {

                log.error("Error occurred", e);

                e.printStackTrace();
            }
        }

}
