package online.shop.online_shop.repository;

import online.shop.online_shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<UserDetails> findByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumberEquals(String phoneNumber);

    Optional<User> findByPhoneNumberEquals(String phoneNumber);

}
