package art.coinExchangeApi.coinExchangeApi.repository;

import art.coinExchangeApi.coinExchangeApi.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller, Long> {
//    List<Seller> findByCoinsToSellGreaterThanEqual(int coinsToSell);
}
