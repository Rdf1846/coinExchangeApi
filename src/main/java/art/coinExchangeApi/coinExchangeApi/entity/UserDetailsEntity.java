package art.coinExchangeApi.coinExchangeApi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name ="user_details")
@Entity
public class UserDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   private String name;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(unique = true, nullable = false)
    private String mobileNumber;

    @Column(unique = true, nullable = false)
    private String email;

    private Double latitude;
    private Double longitude;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "userDetailsEntityCoins", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CoinDenominationDetailsEntity> coinsDenominationList;

}
