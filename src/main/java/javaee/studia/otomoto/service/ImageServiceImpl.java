package javaee.studia.otomoto.service;

import javaee.studia.otomoto.commands.ImageCommand;
import javaee.studia.otomoto.converters.ImageCommandToImage;
import javaee.studia.otomoto.converters.ImageToImageCommand;
import javaee.studia.otomoto.model.Car;
import javaee.studia.otomoto.model.Image;
import javaee.studia.otomoto.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final ImageToImageCommand imageToImageCommand;
    private final ImageCommandToImage imageCommandToImage;
    private final CarRepository carRepository;

    public ImageServiceImpl(ImageToImageCommand imageToImageCommand, ImageCommandToImage imageCommandToImage, CarRepository carRepository) {
        this.imageToImageCommand = imageToImageCommand;
        this.imageCommandToImage = imageCommandToImage;
        this.carRepository = carRepository;
    }

    @Override
    public ImageCommand findByCarIdAndImageId(Long carId, Long imageId) {

        Optional<Car> carOptional = carRepository.findById(carId);

        if (!carOptional.isPresent()){
            //todo impl error handling
            log.error("car id not found. Id: " + carId);
        }

        Car car = carOptional.get();

        Optional<ImageCommand> imageCommandOptional = car.getImages().stream()
                .filter(image -> image.getId().equals(imageId))
                .map( image -> imageToImageCommand.convert(image)).findFirst();

        if(!imageCommandOptional.isPresent()){
            //todo impl error handling
            log.error("Image id not found: " + imageId);
        }

        return imageCommandOptional.get();
    }


    @Override
    @Transactional
    public ImageCommand saveImageCommand(ImageCommand command) {
        Optional<Car> carOptional = carRepository.findById(command.getCarId());

        if(!carOptional.isPresent()){

            //todo toss error if not found!
            log.error("Car not found for id: " + command.getCarId());
            return new ImageCommand();
        } else {
            Car car = carOptional.get();

            Optional<Image> imageOptional = car
                    .getImages()
                    .stream()
                    .filter(image -> image.getId().equals(command.getId()))
                    .findFirst();

            if(imageOptional.isPresent()){
                Image imageFound = imageOptional.get();
                imageFound.setFilename(command.getFilename());
                imageFound.setType(command.getType());
                imageFound.setPic(command.getPic());
            } else {
                //add new Ingredient
                Image image = imageCommandToImage.convert(command);
                image.setCar(car);
                car.addImage(image);
            }

            Car savedCar = carRepository.save(car);

            Optional<Image> savedImageOptional = savedCar.getImages().stream()
                    .filter(carImages -> carImages.getId().equals(command.getId()))
                    .findFirst();

            //check by description
            if(!savedImageOptional.isPresent()){
                //not totally safe... But best guess
                savedImageOptional = savedCar.getImages().stream()
                        .filter(carImages -> carImages.getFilename().equals(command.getFilename()))
                        .filter(carImages -> carImages.getType().equals(command.getType()))
                        .filter(carImages -> carImages.getPic().equals(command.getPic()))
                        .findFirst();
            }

            //to do check for fail
            return imageToImageCommand.convert(savedImageOptional.get());
        }

    }

    @Override
    public void deleteById(Long carId, Long idToDelete) {

        log.debug("Deleting image: " + carId + ":" + idToDelete);

        Optional<Car> carOptional = carRepository.findById(carId);

        if(carOptional.isPresent()){
            Car car = carOptional.get();
            log.debug("found car");

            Optional<Image> imageOptional = car
                    .getImages()
                    .stream()
                    .filter(image -> image.getId().equals(idToDelete))
                    .findFirst();

            if(imageOptional.isPresent()){
                log.debug("found Image");
                Image imageToDelete = imageOptional.get();
                imageToDelete.setCar(null);
                car.getImages().remove(imageOptional.get());
                carRepository.save(car);
            }
        } else {
            log.debug("Car Id Not found. Id:" + carId);
        }
    }

    @Override
    @Transactional
    public void saveImageFile(Long carId, Long imageId, String imageFilename, String imageType, MultipartFile file) {

        try {
            Car car = carRepository.findById(carId).get();

            byte[] byteObjects = new byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            car.addImage(new Image(imageId,imageFilename, imageType, byteObjects));

            carRepository.save(car);
        } catch (IOException e) {
            //todo handle better
            log.error("Error occurred", e);

            e.printStackTrace();
        }
    }
}
