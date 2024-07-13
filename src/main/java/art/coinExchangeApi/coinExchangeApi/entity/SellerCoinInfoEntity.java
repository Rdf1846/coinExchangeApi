package art.coinExchangeApi.coinExchangeApi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Seller_coin_details")
@Entity
public class SellerCoinInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    @Column(name = "coinType", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer coinType;

    @Column(name = "coinsToSell", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer coinsToSell;

}
