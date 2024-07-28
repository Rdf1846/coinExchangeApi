package art.coinExchangeApi.coinExchangeApi.dto;

import art.coinExchangeApi.coinExchangeApi.entity.CoinDenominationDetailsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String userName;
    private String mobileNumber;
    private String email;
    private double latitude;
    private double longitude;

    private String password;
    private List<CoinDenominationDetailsEntity> coinsDenominationList;
}
