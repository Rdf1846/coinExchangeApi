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
@Table(name = "Jpa_Seller_coin_details")
@Entity
public class SellerCoinInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    @Column(name = "coin_Type", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer coin_Type;

    @Column(name = "coins_To_Sell", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer coins_To_Sell;


    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller sellerInSellerCoinInfoEntity;

}
