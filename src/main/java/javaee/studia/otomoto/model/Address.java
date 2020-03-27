package javaee.studia.otomoto.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @NotNull
    @Column(nullable = false)
    private String street;
    @NotNull
    @Column(nullable = false)
    private String city;
    @NotNull
    @Column(nullable = false)
    private String state;
    @NotNull
    @Column(nullable = false)
    private String zip;

}
