package javaee.studia.otomoto.repository;

import javaee.studia.otomoto.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Mateusz Wilk
 * Klasa inicjalizuje bazę danych danymi o utworzonych kontach
 */

@Service
public class DbInit implements CommandLineRunner {
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public DbInit(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {

//        this.userRepository.deleteAll();

        User woj = new User("wojtek", "wojciechpolubiec@gmail.com", passwordEncoder.encode("woj123"), "Wojciech Polubiec", "Bajkowa 6", "Warszawa", "Mazowieckie", "02-777", "510936615", "USER", "ACCESS_TEST1");
        User admin = new User("admin", "admin@gmail.com", passwordEncoder.encode("admin123"), "Administrator", "Sieciowa 1", "Warszawa", "Mazowieckie", "01-111", "321936615", "ADMIN", "ACCESS_TEST1,ACCESS_TEST2");
        User manager = new User("manager", "manager@gmail.com", passwordEncoder.encode("manager123"), "Manager", "Menadżerska 1", "Warszawa", "Mazowieckie", "01-222", "120936615", "MANAGER", "ACCESS_TEST1");

        List<User> users = Arrays.asList(woj, admin, manager);
//        this.userRepository.saveAll(users);


    }


}
