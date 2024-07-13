package art.coinExchangeApi.coinExchangeApi.dto;

import art.coinExchangeApi.coinExchangeApi.entity.BuyerCoinInfoEntity;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class BuyerDto {

    private Long id;
    private String name;
    private String mobileNumber;
    private String email;
    private double latitude;
    private double longitude;

   private List<BuyerCoinInfoEntity> buyerCoinInfoEntity;

//    private int coinsToBuy;
//    private int coinType;
}
