package javaee.studia.otomoto.service;

import javaee.studia.otomoto.model.Car;
import javaee.studia.otomoto.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final CarRepository carRepository;

    public ImageServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    @Override
    @Transactional
    public void saveImageFile(Long carId, MultipartFile file) {

        try {
            Car car = carRepository.findById(carId).get();
            Byte[] byteObjects = new Byte[file.getBytes().length];
            int i = 0;

            for (byte b : file.getBytes()) {
                byteObjects[i++] = b;
            }

            car.setImage(byteObjects);
            carRepository.save(car);

        } catch (IOException e) {
            //todo handle better
           log.error("Error occurred", e);

            e.printStackTrace();
        }
    }



}
