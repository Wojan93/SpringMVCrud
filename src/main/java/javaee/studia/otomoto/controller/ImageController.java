package javaee.studia.otomoto.controller;

import javaee.studia.otomoto.commands.CarCommand;
import javaee.studia.otomoto.commands.ImageCommand;
import javaee.studia.otomoto.model.Image;
import javaee.studia.otomoto.service.CarService;
import javaee.studia.otomoto.service.ImageService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;


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

        return "cars/imageuploadform";
    }

    @PostMapping("car/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file) {

        imageService.saveImageFile(Long.valueOf(id), Long.valueOf(id), "imageFilename" + id, ".jpg", file);

        return "redirect:/cars/" + id + "/show";
    }

    @GetMapping("cars/{id}/carimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
        CarCommand carCommand = carService.findCommandById(Long.valueOf(id));

        if (carCommand.getImages() != null) {

            Set<Image> images = new HashSet<>();
            images = carCommand.getImages();
            for (Image ic : images) {
                byte[] byteArray = new byte[carCommand.ic.getPic().length];
                int i = 0;

                for (Byte wrappedByte : carCommand.ic.getPic()) {
                    byteArray[i++] = wrappedByte; //auto unboxing
                }

                response.setContentType("image/jpeg");
                InputStream is = new ByteArrayInputStream(byteArray);
                IOUtils.copy(is, response.getOutputStream());
            }
        }
    }
}
