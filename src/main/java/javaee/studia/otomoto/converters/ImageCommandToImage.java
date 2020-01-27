package javaee.studia.otomoto.converters;

import javaee.studia.otomoto.commands.ImageCommand;
import javaee.studia.otomoto.model.Car;
import javaee.studia.otomoto.model.Image;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ImageCommandToImage implements Converter<ImageCommand, Image> {

    public ImageCommandToImage() {}





    @Synchronized
    @Nullable
    @Override
    public Image convert(ImageCommand source) {
        if (source == null) {
            return null;
        }
        final Image image = new Image();
        image.setId(source.getId());

        if(source.getCarId() != null){
            Car car = new Car();
            car.setId(source.getCarId());
            image.setCar(car);
            car.addImage(image);
        }

        image.setFilename(source.getFilename());
        image.setType(source.getType());
        image.setPic(source.getPic());

        return image;
    }


}
