package art.coinExchangeApi.coinExchangeApi.repository;

import art.coinExchangeApi.coinExchangeApi.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {
}
