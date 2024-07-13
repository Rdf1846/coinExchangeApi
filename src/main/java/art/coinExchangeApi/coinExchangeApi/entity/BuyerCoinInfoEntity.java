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
@Table(name = "Buyer_coin_details")
@Entity
public class BuyerCoinInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int ids;

    @Column(name = "coinType", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer coinType;

    @Column(name = "coinsToBuy", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer coinsToBuy;

}
