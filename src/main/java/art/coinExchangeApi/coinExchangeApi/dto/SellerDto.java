package art.coinExchangeApi.coinExchangeApi.dto;

import art.coinExchangeApi.coinExchangeApi.entity.SellerCoinInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SellerDto {

    private Long id;
    private String name;
    private String mobileNumber;
    private String email;
    private double latitude;
    private double longitude;

    private List<SellerCoinInfoEntity> sellerCoinInfoEntity;
}
