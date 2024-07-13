package art.coinExchangeApi.coinExchangeApi.customResponseEntity;

import art.coinExchangeApi.coinExchangeApi.dto.BuyerDto;
import art.coinExchangeApi.coinExchangeApi.dto.SellerDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BuyerCustomResponseEntity<T, E> {
    private T buyerDto;
    private List<SellerDto> sellerDtoList;

    public BuyerCustomResponseEntity(T buyerDto, List<SellerDto> sellerDtoList)
    {
        this.buyerDto = buyerDto;
        this.sellerDtoList = sellerDtoList;
    }
}
