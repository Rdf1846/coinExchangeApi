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
@Table(name = "Jpa_Buyer_coin_details")
@Entity
public class BuyerCoinInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int ids;

    @Column(name = "coin_Type", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer coin_Type;

    @Column(name = "coins_To_Buy", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer coins_To_Buy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    private Buyer buyerInBuyerCoinInfoEntity;
}
