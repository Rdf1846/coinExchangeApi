package art.coinExchangeApi.coinExchangeApi.service;

import art.coinExchangeApi.coinExchangeApi.dto.BuyerDto;
import art.coinExchangeApi.coinExchangeApi.dto.SellerDto;
import art.coinExchangeApi.coinExchangeApi.dto.UserDto;
import art.coinExchangeApi.coinExchangeApi.entity.BuyerCoinInfoEntity;

import java.util.List;

public interface CoinExchangeService {

    SellerDto registerSeller(SellerDto sellerDto);

    BuyerDto registerBuyer(BuyerDto buyerDto);

    List<SellerDto> findSellers(List<BuyerCoinInfoEntity> buyerCoinInfoList);

    UserDto registerUserDetails(UserDto userDto);


}
