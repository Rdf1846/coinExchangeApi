package art.coinExchangeApi.coinExchangeApi.repository;

import art.coinExchangeApi.coinExchangeApi.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
