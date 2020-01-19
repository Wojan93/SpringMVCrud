package javaee.studia.otomoto.security;

import javaee.studia.otomoto.model.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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

    public RegistrationForm() {
    }

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username, email, passwordEncoder.encode(password), fullname, street, city, state, zip, phone);
    }

}

