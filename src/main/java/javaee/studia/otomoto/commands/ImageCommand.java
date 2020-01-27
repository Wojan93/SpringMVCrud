package javaee.studia.otomoto.commands;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ImageCommand {

    private Long id;
    private Long carId;
    private String filename;
    private String type;
    private Byte[] pic;
}
