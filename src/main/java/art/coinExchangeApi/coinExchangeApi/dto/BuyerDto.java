package art.coinExchangeApi.coinExchangeApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BuyerDto {

    private Long id;
    private String name;
    private String mobileNumber;
    private String email;
    private double latitude;
    private double longitude;
    private int coinsToBuy;
    private int coinType;
}
