package javaee.studia.otomoto.controller;

import javaee.studia.otomoto.model.Car;
import javaee.studia.otomoto.model.UserPrincipal;
import javaee.studia.otomoto.repository.CarRepository;
import javax.validation.Valid;
import javaee.studia.otomoto.repository.UserRepository;
import javaee.studia.otomoto.security.UserPrincipalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Mateusz Wilk
 *
 * Klasa kontrolera obsługujący interakcje użytkownika z ogłoszeniami zamieszczanymi na portalu
 */
@Controller
public class CarController {

    private CarRepository carRepository;
    private UserRepository userRepository;
    private UserPrincipalDetailsService userDetailsService;
    private UserPrincipal userPrincipal;


    /**
     * Konstruktor czteroargumentowy z wstrzyknięciem niezbędnych komponentów zapewniających obsługę operacji na bazie danych itd.
     * @param carRepository
     * @param userRepository
     * @param userDetailsService
     * @param userPrincipal
     */
    @Autowired
    public CarController(CarRepository carRepository, UserRepository userRepository, UserPrincipalDetailsService userDetailsService, UserPrincipal userPrincipal) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
        this.userPrincipal = userPrincipal;
    }

    /**
     * Metoda usuwająca znaki Spacji z przodu i z tyłu wprowadzanego ciągu znaków
     */
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    /**
     * Metoda wyświetlająca odpowiedni formularz na potrzeby dodawania ogłoszenia
     * @param model przechowujący objekt car
     * @return String będący nazwą szablonu, który ma zostać wyświetlony
     */
    @RequestMapping(path = "/cars/add", method = RequestMethod.GET)
    public String createProduct(Model model) {
        model.addAttribute("car", new Car());
        return "addnew";
    }

    /**
     * Metoda dodająca nowe ogłoszenie z jednoczesną walidacją poprawności wprowadzonych danych
     * @param car
     * @param bindingResult
     * @return String będący nazwą szablonu, który ma zostać wyświetlony po zapisaniu samochodu w bazie
     */
    @RequestMapping(path = "cars", method = RequestMethod.POST)
    public String saveCar(@Valid @ModelAttribute("car") Car car, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "edit";
        } else {
            String username = getUsername();
            car.setSeller(userRepository.findByUsername(username).getId());
            carRepository.save(car);
            return "redirect:/";
        }
    }

    /**
     * Metoda wyświetlająca listę samochodów bądących obecnie na sprzedaż
     * @param model przechowujący listę samochodów do wyświetlenia
     * @return String będący nazwą szablonu, który ma zostać wyświetlony
     */
    @RequestMapping(path = "/cars", method = RequestMethod.GET)
    public String getAllCars(Model model) {
        model.addAttribute("cars", carRepository.findByBuyerIsNull());
        return "cars";
    }

    /**
     * Metoda wyświetlająca listę samochodów, które zakupił dany użytkownik
     * @param model przechowujący listę samochodów do wyświetlenia
     * @return String będący nazwą szablonu, który ma zostać wyświetlony
     */
    @RequestMapping(path = "/cars/buyed", method = RequestMethod.GET)
    public String getBuyedCars(Model model) {

        String username = getUsername();

        model.addAttribute("cars", carRepository.findByBuyer(userRepository.findByUsername(username).getId()));
        return "cars-buyed";
    }

    /**
     * Metoda wyświetlająca listę samochodów, które ma na sprzedaż dany użytkownik
     * @param model przechowujący listę samochodów do wyświetlenia
     * @return String będący nazwą szablonu, który ma zostać wyświetlony
     */
    @RequestMapping(path = "/cars/forsale", method = RequestMethod.GET)
    public String getForSaleCars(Model model) {

        String username = getUsername();

        model.addAttribute("cars", carRepository.findBySeller(userRepository.findByUsername(username).getId()));
        return "cars-forsale";
    }


    /**
     * Metoda pozwalająca edytować wybrane ogłoszenie
     * @param model przechowujący listę samochodów do wyświetlenia
     * @param
     * @return String będący nazwą szablonu, który ma zostać wyświetlony
     */
    @RequestMapping(path = "/cars/edit/{id}", method = RequestMethod.GET)
    public String editCar(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("car", carRepository.findById(id));
        return "edit";
    }

    /**
     * Metoda pozwalająca na "zakup" samochodu
     * @param id będący identyfikatorem kupowanego samochodu
     * @return String będący nazwą szablonu, który ma zostać wyświetlony
     */
    @RequestMapping(path = "/cars/buy/{id}", method = RequestMethod.GET)
    public String buyCar(@PathVariable(name = "id") Long id) {
        Car car = carRepository.getOne(id);

        String username = getUsername();

        car.setBuyer(userRepository.findByUsername(username).getId());
        carRepository.save(car);
        return "redirect:/cars";
    }

    /**
     * Metoda pozwalająca na kasowanie ogłoszenia
     * @param id będący identyfikatorem usuwanego samochodu
     * @return String będący nazwą szablonu, który ma zostać wyświetlony
     */
    @RequestMapping(path = "/cars/delete/{id}", method = RequestMethod.GET)
    public String deleteCar(@PathVariable(name = "id") Long id) {
        carRepository.deleteById(id);
        return "redirect:/cars";
    }

    /**
     * Metoda zwracającanazwę zalogowanego użytkownika
     * @return String będący nazwą zalogowanego użytkownika
     */
    private String getUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
}
