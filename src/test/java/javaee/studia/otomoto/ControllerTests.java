package javaee.studia.otomoto;
import javaee.studia.otomoto.commands.CarCommand;
import javaee.studia.otomoto.controller.CarController;
import javaee.studia.otomoto.model.Car;
import javaee.studia.otomoto.model.UserPrincipal;
import javaee.studia.otomoto.repository.CarRepository;
import javaee.studia.otomoto.repository.UserRepository;
import javaee.studia.otomoto.security.UserPrincipalDetailsService;
import javaee.studia.otomoto.service.CarService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ControllerTests {

    @Mock
    CarService carService;

    CarRepository carRepository;

    UserRepository userRepository;

    UserPrincipalDetailsService userDetailsService;

    UserPrincipal userPrincipal;

    CarController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new CarController(carRepository, userRepository, userDetailsService, userPrincipal);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testCreateProduct() throws Exception {

        Car car = new Car();
        car.setId(1L);

        when(carService.findById(anyLong())).thenReturn(car);

        mockMvc.perform(get("/cars/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("addnew"))
                .andExpect(model().attributeExists("car"));
    }

}