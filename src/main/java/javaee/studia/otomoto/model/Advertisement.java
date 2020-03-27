package javaee.studia.otomoto.model;

import lombok.*;
import javax.persistence.*;



@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String textAd;

}
