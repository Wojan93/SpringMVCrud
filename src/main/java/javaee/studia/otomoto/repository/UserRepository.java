package javaee.studia.otomoto.repository;

import javaee.studia.otomoto.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
