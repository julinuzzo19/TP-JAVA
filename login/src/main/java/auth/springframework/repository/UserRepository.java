package auth.springframework.repository;

import auth.springframework.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    //public User findByUserNameAndPassword(String userName, String password);
    public User findByEmailAndPassword(String email, String password);
    public User findByEmail(String email);


}
