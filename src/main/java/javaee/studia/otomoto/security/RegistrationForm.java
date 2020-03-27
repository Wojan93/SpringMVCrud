package javaee.studia.otomoto.security;

import javaee.studia.otomoto.model.Address;
import javaee.studia.otomoto.model.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
public class RegistrationForm {

    @NotNull(message = "is required")
    @Size(min = 2, message = "at least 2 signs")
    private String username;

    @NotNull(message = "is required")
    @Email(message = "must be valid")
    private String email;

    @NotNull(message = "is required")
    @Size(min = 3, message = "at least 3 signs")
    private String password;

    @NotNull(message = "is required")
    @Size(min = 3, message = "at least 3 signs")
    private String passwordConfirmation;

    @NotNull(message = "is required")
    @Size(min = 2, message = "at least 2 signs")
    private String fullname;

    @NotNull(message = "is required")
    @Size(min = 2, message = "at least 2 signs")
    private String street;

    @NotNull(message = "is required")
    @Size(min = 2, message = "at least 2 signs")
    private String city;

    @NotNull(message = "is required")
    @Size(min = 2, message = "at least 2 signs")
    private String state;

    @NotNull(message = "is required")
    @Pattern(regexp = "^[0-9][0-9][-][0-9][0-9][0-9]$", message = "must be in xx-xxx format (no letters)")
    private String zip;

    @NotNull(message = "is required")
    @Pattern(regexp = "^[^a-zA-Z]+", message = "phone number must be valid (no letters)")
    private String phone;

    public RegistrationForm() {
    }



    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username, email, passwordEncoder.encode(password), fullname, new Address(street, city, state, zip), phone);
    }


    public String getUsername() {
        return username;
    }

    public String getEmail() {return email;}

    public String getPassword() {
        return password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }
}

