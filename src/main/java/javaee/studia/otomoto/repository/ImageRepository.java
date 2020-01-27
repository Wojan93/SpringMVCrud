package javaee.studia.otomoto.repository;

import javaee.studia.otomoto.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository  extends JpaRepository<Image, Long> {
}
