package art.coinExchangeApi.coinExchangeApi.repository;

import art.coinExchangeApi.coinExchangeApi.entity.UserDetailsEntity;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetailsEntity, Long> {

}
