package javaee.studia.otomoto.security;

import javaee.studia.otomoto.model.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {


    private String username;
    private String email;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username, email, passwordEncoder.encode(password), fullname, street, city, state, zip, phone);
    }

}

