package clearsolution.pytaichuk.test.repository;

import clearsolution.pytaichuk.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findUserByBirthDateBetween(LocalDate from, LocalDate to);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByTelephoneNumber(String telephoneNumber);
}
