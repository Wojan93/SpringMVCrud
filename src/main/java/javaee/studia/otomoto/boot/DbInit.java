package javaee.studia.otomoto.boot;

import javaee.studia.otomoto.model.Address;
import javaee.studia.otomoto.model.User;
import javaee.studia.otomoto.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

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

        this.userRepository.deleteAll();

        Address wojAddress = new Address("Bajkowa","Warszawa","mazowieckie","02-777");
        Address adminAddress = new Address("Administracyjna","Kraków","małopolskie","20-777");
        Address managerAddress = new Address("Menadżerska","Warszawa","mazowieckie","12-777");


        User woj = new User("wojtek", "wojciechpolubiec@gmail.com", passwordEncoder.encode("woj123"), "Wojciech Polubiec", wojAddress, "510936615", "USER", "ACCESS_TEST1");
        User admin = new User("admin", "admin@gmail.com", passwordEncoder.encode("admin123"), "Administrator", adminAddress, "321936615", "ADMIN", "ACCESS_TEST1,ACCESS_TEST2");
        User manager = new User("manager", "manager@gmail.com", passwordEncoder.encode("manager123"), "Manager", managerAddress, "120936615", "MANAGER", "ACCESS_TEST1");

        List<User> users = Arrays.asList(woj, admin, manager);
        this.userRepository.saveAll(users);


    }


}
