package javaee.studia.otomoto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class OtomotoApplication {

    public static void main(String[] args) {
        SpringApplication.run(OtomotoApplication.class, args);
    }

}
