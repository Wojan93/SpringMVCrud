package javaee.studia.otomoto;

import javaee.studia.otomoto.commands.CarCommand;
import javaee.studia.otomoto.controller.ImageController;
import javaee.studia.otomoto.service.CarService;
import javaee.studia.otomoto.service.ImageService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ImageTests {

    @Mock
    ImageService imageService;

    @Mock
    CarService carService;

    ImageController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new ImageController(imageService, carService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getImageForm() throws Exception {
        //given
        CarCommand command = new CarCommand();
        command.setId(1L);

        when(carService.findCommandById(anyLong())).thenReturn(command);

        //when
        mockMvc.perform(get("/cars/1/image"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("car"));

        verify(carService, times(1)).findCommandById(anyLong());

    }

    @Test
    public void handleImagePost() throws Exception {
        MockMultipartFile multipartFile =
                new MockMultipartFile("imagefile", "testing.txt", "text/plain",
                        "otomoto".getBytes());

        mockMvc.perform(multipart("/cars/1/image").file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/cars"));

        verify(imageService, times(1)).saveImageFile(anyLong(), any());
    }


    @Test
    public void renderImageFromDB() throws Exception {

        //given
        CarCommand command = new CarCommand();
        command.setId(1L);

        String s = "fake image text";
        Byte[] bytesBoxed = new Byte[s.getBytes().length];

        int i = 0;

        for (byte primByte : s.getBytes()){
            bytesBoxed[i++] = primByte;
        }

        command.setImage(bytesBoxed);

        when(carService.findCommandById(anyLong())).thenReturn(command);

        //when
        MockHttpServletResponse response = mockMvc.perform(get("/cars/1/carimage"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        byte[] reponseBytes = response.getContentAsByteArray();

        assertEquals(s.getBytes().length, reponseBytes.length);
    }
}