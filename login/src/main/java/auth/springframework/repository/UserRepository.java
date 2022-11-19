package auth.springframework.repository;

import auth.springframework.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    //public User findByUserNameAndPassword(String userName, String password);
    public User findByEmailAndPassword(String email, String password);
    public User findByEmail(String email);


}
