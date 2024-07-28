package art.coinExchangeApi.coinExchangeApi.service;

import art.coinExchangeApi.coinExchangeApi.dto.BuyerDto;
import art.coinExchangeApi.coinExchangeApi.dto.SellerDto;
import art.coinExchangeApi.coinExchangeApi.dto.UserDto;
import art.coinExchangeApi.coinExchangeApi.entity.BuyerCoinInfoEntity;

import java.util.List;
import java.util.Map;

public interface CoinExchangeService {

    SellerDto registerSeller(SellerDto sellerDto);

    BuyerDto registerBuyer(BuyerDto buyerDto);

    List<SellerDto> findSellers(List<BuyerCoinInfoEntity> buyerCoinInfoList);

    UserDto registerUserDetails(UserDto userDto);

    boolean verifyUserPassword(Map<String, String> request);

    String updateInUserDetailsEntity(String userName, UserDto userDto);

    UserDto getUserDetailsByUserName(String userName);


}
