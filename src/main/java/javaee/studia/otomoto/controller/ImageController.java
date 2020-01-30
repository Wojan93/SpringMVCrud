package javaee.studia.otomoto.controller;

import javaee.studia.otomoto.commands.CarCommand;
import javaee.studia.otomoto.commands.ImageCommand;
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

@Slf4j
@Controller
public class ImageController {

    private final ImageService imageService;
    private final CarService carService;

    public ImageController(ImageService imageService, CarService carService) {
        this.imageService = imageService;
        this.carService = carService;
    }

    @GetMapping("/car/{carId}/images")
    public String listImages(@PathVariable String carId, Model model){
        log.debug("Getting images list for car id: " + carId);

        // use command object to avoid lazy load errors in Thymeleaf.
        model.addAttribute("car", carService.findCommandById(Long.valueOf(carId)));

        return "car/image/list";
    }

    @GetMapping("car/{carId}/image/{id}/show")
    public String showCarImage(@PathVariable String carId,
                                       @PathVariable String id, Model model){
        model.addAttribute("image", imageService.findByCarIdAndImageId(Long.valueOf(carId), Long.valueOf(id)));
        return "car/image/show";
    }


    @GetMapping("car/{carId}/image/new")
    public String newCar(@PathVariable String carId, Model model){

        //make sure we have a good id value
        CarCommand carCommand = carService.findCommandById(Long.valueOf(carId));
        //todo raise exception if null

        //need to return back parent id for hidden form property
        ImageCommand imageCommand = new ImageCommand();
        imageCommand.setCarId(Long.valueOf(carId));
        model.addAttribute("image", imageCommand);

        return "car/image/imageform";
    }


    @GetMapping("car/{carId}/image/{id}/update")
    public String updateCarImage(@PathVariable String carId,
                                         @PathVariable String id, Model model){
        model.addAttribute("image", imageService.findByCarIdAndImageId(Long.valueOf(carId), Long.valueOf(id)));

        return "car/image/imageform";
    }

    @PostMapping("car/{carId}/image")
    public String saveOrUpdate(@ModelAttribute ImageCommand command){
        ImageCommand savedCommand = imageService.saveImageCommand(command);

        log.debug("saved car id:" + savedCommand.getCarId());
        log.debug("saved image id:" + savedCommand.getId());

        return "redirect:/car/" + savedCommand.getCarId() + "/image/" + savedCommand.getId() + "/show";
    }

    @GetMapping("car/{carId}/image/{id}/delete")
    public String deleteImage(@PathVariable String carId,
                                   @PathVariable String id){

        log.debug("deleting image id:" + id);
        imageService.deleteById(Long.valueOf(carId), Long.valueOf(id));

        return "redirect:/car/" + carId + "/images";
    }

    @GetMapping("car/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model){
        model.addAttribute("car", carService.findCommandById(Long.valueOf(id)));

        return "cars/imageuploadform";
    }

    @PostMapping("car/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file){

        imageService.saveImageFile(Long.valueOf(id), Long.valueOf(id), "imageFilename" + id, ".jpg", file);

        return "redirect:/cars/" + id + "/show";
    }

    @GetMapping("car/{carId}/carimage/{id}")
    public void renderImageFromDB(@PathVariable String carId, @PathVariable String id, HttpServletResponse response) throws IOException {
        ImageCommand imageCommand = imageService.findByCarIdAndImageId(Long.valueOf(carId), Long.valueOf(id));



        if (imageCommand.getPic() != null) {
            byte[] byteArray = new byte[imageCommand.getPic().length];
            int i = 0;

            for (Byte wrappedByte : imageCommand.getPic()){
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}
