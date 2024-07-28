package art.coinExchangeApi.coinExchangeApi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name ="coinDenomination_details")
@Entity
public class CoinDenominationDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "coin_Type", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer coin_Type;

    @Column(name = "number_of_coins", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer number_of_coins;

    @Column(name = "total", columnDefinition = "INT DEFAULT 0")
    private Integer total;

    @Column(name = "transaction_type_SellOrBuy", columnDefinition = "VARCHAR(50) DEFAULT 'buy'")
    private String transaction_type_SellOrBuy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserDetailsEntity userDetailsEntityCoins;


}
