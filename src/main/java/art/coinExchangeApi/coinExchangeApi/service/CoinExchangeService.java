package art.coinExchangeApi.coinExchangeApi.service;

import art.coinExchangeApi.coinExchangeApi.dto.BuyerDto;
import art.coinExchangeApi.coinExchangeApi.dto.SellerDto;
import art.coinExchangeApi.coinExchangeApi.entity.Buyer;
import art.coinExchangeApi.coinExchangeApi.entity.Seller;

import java.util.List;

public interface CoinExchangeService {

    SellerDto registerSeller(SellerDto sellerDto);

    BuyerDto registerBuyer(BuyerDto buyerDto);

    List<SellerDto> findSellers(int coinsToBuy);


}
