package javaee.studia.otomoto.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Data
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Size(min=2, max=30, message = "Nazwa musi zawierać od 2 do 30 znaków")
    private String username;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
    @NotNull
    @Size(min=2, max=80, message = "Nazwa musi zawierać od 2 do 80 znaków")
    private String fullname;
    @NotNull
    @Size(min=4, max=60, message = "Nazwa musi zawierać od 4 do 60 znaków")
    private String street;
    @NotNull
    @Size(min=2, max=30, message = "Nazwa musi zawierać od 2 do 30 znaków")
    private String city;
    @NotNull
    @Size(min=5, max=30, message = "Nazwa musi zawierać od 5 do 30 znaków")
    private String state;
    @NotNull
    @Pattern(regexp = "^[0-9][0-9][-][0-9][0-9][0-9]$")
    private String zip;
    @NotNull
    @Digits(integer = 12, fraction = 0, message = "Numer musi zawierać od 7 do 12 znaków")
    private String phoneNumber;

    public User(String username, String email, String password, String fullname, String street, String city, String state, String zip, String phoneNumber) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
    }
    public User() {}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
