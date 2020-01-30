package javaee.studia.otomoto.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"car"})
@Entity
public class Image {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Car car;

    private String filename;

    private String type;

    @Lob
    private byte[] pic;

    public Image() {
    }

    public Image(long id, String filename, String type, byte[] pic) {
        this.id = id;
        this.filename = filename;
        this.type = type;
        this.pic = pic;
    }

    public Image(long id, String filename, String type, byte[] pic, Car car) {
        this.id = id;
        this.filename = filename;
        this.type = type;
        this.pic = pic;
        this.car = car;
    }


}
