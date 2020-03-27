package javaee.studia.otomoto.controller;


import javaee.studia.otomoto.commands.CarCommand;
import javaee.studia.otomoto.commands.MotorcycleCommand;
import javaee.studia.otomoto.service.CarService;
import javaee.studia.otomoto.service.ImageService;
import javaee.studia.otomoto.service.MtService;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Controller
public class MtImageController {

    private final ImageService imageService;
    private final MtService mtService;

    public MtImageController(ImageService imageService, MtService mtService) {
        this.imageService = imageService;
        this.mtService = mtService;
    }

    @GetMapping("/main/motorcycles/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model) {
        model.addAttribute("car", mtService.findCommandById(Long.valueOf(id)));

        return "imageuploadform";
    }

    @PostMapping("/main/motorcycles/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file) {

        imageService.saveImageFile(Long.valueOf(id), file);

        return "redirect:/main/motorcycles";
    }

    @GetMapping("/main/motorcycles/{id}/mtimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {

        MotorcycleCommand mtCommand = mtService.findCommandById(Long.valueOf(id));

        if (mtCommand.getImage() != null) {
            byte[] byteArray = new byte[mtCommand.getImage().length];
            int i = 0;

            for (Byte wrappedByte : mtCommand.getImage()) {
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}
