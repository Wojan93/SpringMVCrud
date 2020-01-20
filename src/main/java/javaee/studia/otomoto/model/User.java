package javaee.studia.otomoto.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
public class User {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(nullable = false)
    @Size(min = 2, max = 30, message = "Nazwa musi zawierać od 2 do 30 znaków")
    private String username;
    @Column(nullable = false)
    @Email
    private String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @Size(min = 2, max = 80, message = "Nazwa musi zawierać od 2 do 80 znaków")
    private String fullname;
    @Column(nullable = false)
    @Size(min = 4, max = 60, message = "Nazwa musi zawierać od 4 do 60 znaków")
    private String street;
    @Column(nullable = false)
    @Size(min = 2, max = 30, message = "Nazwa musi zawierać od 2 do 30 znaków")
    private String city;
    @Column(nullable = false)
    @Size(min = 5, max = 30, message = "Nazwa musi zawierać od 5 do 30 znaków")
    private String state;
    @Column(nullable = false)
    @Pattern(regexp = "^[0-9][0-9][-][0-9][0-9][0-9]$")
    private String zip;
    @Column(nullable = false)
    @Digits(integer = 12, fraction = 0, message = "Numer musi zawierać od 7 do 12 znaków")
    private String phoneNumber;

    private int active;

    private String roles = "";

    private String permissions = "";

    public User() {
    }

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

    public User(String username, String email, String password, String fullname, String street, String city, String state, String zip, String phoneNumber, String roles, String permissions) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
        this.permissions = permissions;
        this.active = 1;
    }

    public int getActive() {
        return active;
    }

    public List<String> getRoleList() {
        if (this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionList() {
        if (this.permissions.length() > 0) {
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }


}
