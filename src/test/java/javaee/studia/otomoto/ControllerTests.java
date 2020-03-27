package javaee.studia.otomoto;
import javaee.studia.otomoto.controller.CarController;
import javaee.studia.otomoto.model.CarAdvertisement;
import javaee.studia.otomoto.model.UserPrincipal;
import javaee.studia.otomoto.repository.CarAdRepository;
import javaee.studia.otomoto.repository.MtAdRepository;
import javaee.studia.otomoto.repository.UserRepository;
import javaee.studia.otomoto.security.UserPrincipalDetailsService;
import javaee.studia.otomoto.service.CarService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author Wojciech Polubiec
 */

public class ControllerTests {

    @Mock
    CarService carService;

    CarAdRepository carAdRepository;

    MtAdRepository mtAdRepository;

    UserRepository userRepository;

    UserPrincipalDetailsService userDetailsService;

    UserPrincipal userPrincipal;

    CarController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new CarController(carAdRepository, mtAdRepository, userRepository, userDetailsService, userPrincipal);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testCreateProduct() throws Exception {

        CarAdvertisement carAdvertisement = new CarAdvertisement();
        carAdvertisement.setId(1L);

        when(carService.findCarById(anyLong())).thenReturn(carAdvertisement);

        mockMvc.perform(get("/cars/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("addnew"))
                .andExpect(model().attributeExists("car"));
    }

}