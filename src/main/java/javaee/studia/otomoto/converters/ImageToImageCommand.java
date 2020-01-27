package javaee.studia.otomoto.converters;

import javaee.studia.otomoto.commands.ImageCommand;
import javaee.studia.otomoto.model.Image;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ImageToImageCommand implements Converter<Image, ImageCommand> {

    public ImageToImageCommand() {}

    @Synchronized
    @Nullable
    @Override
    public ImageCommand convert(Image image) {
        if (image == null) {
            return null;
        }

        ImageCommand imageCommand = new ImageCommand();
        imageCommand.setId(image.getId());
        if (image.getCar() != null) {
            imageCommand.setCarId(image.getCar().getId());
        }
        imageCommand.setFilename(image.getFilename());
        imageCommand.setType(image.getType());
        imageCommand.setPic(image.getPic());
        return imageCommand;
    }
}
