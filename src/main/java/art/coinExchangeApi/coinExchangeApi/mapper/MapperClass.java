package art.coinExchangeApi.coinExchangeApi.mapper;

import art.coinExchangeApi.coinExchangeApi.dto.BuyerDto;
import art.coinExchangeApi.coinExchangeApi.dto.SellerDto;
import art.coinExchangeApi.coinExchangeApi.entity.Buyer;
import art.coinExchangeApi.coinExchangeApi.entity.Seller;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

public class MapperClass {

    public static Seller mapSellerDtoToSellerJpaEntity(@NotNull SellerDto sellerDto)
    {
        Seller seller = new Seller(
                sellerDto.getId(),
                sellerDto.getName(),
                sellerDto.getMobileNumber(),
                sellerDto.getEmail(),
                sellerDto.getLatitude(),
                sellerDto.getLongitude(),
                sellerDto.getSellerCoinInfoEntity()
//                sellerDto.getCoinsToSell(),
//                sellerDto.getCoinType()
        );
        return seller;
    }

    public static SellerDto mapSellerJpaEntityToSellerDto(@NotNull Seller seller)
    {
        SellerDto sellerDto = new SellerDto(
                seller.getId(),
                seller.getName(),
                seller.getMobileNumber(),
                seller.getEmail(),
                seller.getLatitude(),
                seller.getLongitude(),
                seller.getSellerCoinInfoEntity()
//                seller.getCoinsToSell(),
//                seller.getCoinType()
        );
        return sellerDto;
    }

    public static Buyer mapBuyerDtoToBuyerJpaEntity(@NotNull BuyerDto buyerDto)
    {
        Buyer buyer = new Buyer(
                buyerDto.getId(),
                buyerDto.getName(),
                buyerDto.getMobileNumber(),
                buyerDto.getEmail(),
                buyerDto.getLatitude(),
                buyerDto.getLongitude(),
                buyerDto.getBuyerCoinInfoEntity()
//                buyerDto.getCoinsToBuy(),
//                buyerDto.getCoinType()
        );
        return buyer;
    }

    public static BuyerDto mapBuyerJpaEntityToBuyerDTo(@NotNull Buyer buyer)
    {
        BuyerDto buyerDto = new BuyerDto(
                buyer.getId(),
                buyer.getName(),
                buyer.getMobileNumber(),
                buyer.getEmail(),
                buyer.getLatitude(),
                buyer.getLongitude(),
                buyer.getBuyerCoinInfoEntity()
//                buyer.getCoinsToBuy(),
//                buyer.getCoinType()
        );
        return buyerDto;
    }

}
