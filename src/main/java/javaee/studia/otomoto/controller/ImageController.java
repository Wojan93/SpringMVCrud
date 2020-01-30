package javaee.studia.otomoto.controller;

import javaee.studia.otomoto.commands.CarCommand;
import javaee.studia.otomoto.service.CarService;
import javaee.studia.otomoto.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Wojciech Polubiec
 */

@Slf4j
@Controller
public class ImageController {

    private final ImageService imageService;
    private final CarService carService;

    public ImageController(ImageService imageService, CarService carService) {
        this.imageService = imageService;
        this.carService = carService;
    }

    @GetMapping("cars/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model) {
        model.addAttribute("car", carService.findCommandById(Long.valueOf(id)));

        return "imageuploadform";
    }

    @PostMapping("cars/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file) {

        imageService.saveImageFile(Long.valueOf(id), file);

        return "redirect:/cars";
    }

    @GetMapping("cars/{id}/carimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {

        CarCommand carCommand = carService.findCommandById(Long.valueOf(id));

        if (carCommand.getImage() != null) {
            byte[] byteArray = new byte[carCommand.getImage().length];
            int i = 0;

            for (Byte wrappedByte : carCommand.getImage()) {
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}
