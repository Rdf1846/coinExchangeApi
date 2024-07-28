package art.coinExchangeApi.coinExchangeApi.repository;

import art.coinExchangeApi.coinExchangeApi.entity.UserDetailsEntity;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserDetailsEntity, Long> {

    Optional<UserDetailsEntity> findByUserName(String userName);
    Optional<UserDetailsEntity> findByEmail(String email);
    Optional<UserDetailsEntity> findByMobileNumber(String mobileNumber);



}
