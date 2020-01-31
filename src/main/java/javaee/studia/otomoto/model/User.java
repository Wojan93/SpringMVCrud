package javaee.studia.otomoto.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Jakub Grzechnik
 */

@Entity
@Data
public class User {

    /**
     * serialVersionUID to pole statyczne - mogą z niej korzystać tylko metody statyczne i finalne - do pola można przypisać wartość tylko jeden raz.
     */

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * @Column (nullable = false) określa, że pole ma być kolumną w tabeli, gdzie nie dopuszczalne są wartości null
     */
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String fullname;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private String zip;
    @Column(nullable = false)
    private String phoneNumber;
    private int active;
    /**
     * roles określa role użytkowników, np. admin lub zwykły użytkownik
     */
    private String roles = "";
    /**
     * permissions określa pozwolenia użytkowników do używania poszczególnych elementów aplikacji, np. API
     */
    private String permissions = "";

    public User() {
    }

    /**
     * Standardowo utworzony na stronie użytkownik będzie miał standardową rolę USER
     */
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
        this.roles = "USER";
        this.permissions = "ACCESS_TEST1";
        this.active = 1;
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

    public Long getId() { return id;}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getActive() {
        return active;
    }

    public String getPhoneNumber() {return phoneNumber;}

    /**
     * Role mogą być oddzielone za pomocą przecinka, użytkownik może mieć wiele ról
     */
    public List<String> getRoleList() {
        if (this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }
    /**
     * Dostępy "permissions" mogą być oddzielone za pomocą przecinka, użytkownik może mieć wiele ról
     */
    public List<String> getPermissionList() {
        if (this.permissions.length() > 0) {
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }


}
