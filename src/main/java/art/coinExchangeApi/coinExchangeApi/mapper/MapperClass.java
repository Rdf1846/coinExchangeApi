package art.coinExchangeApi.coinExchangeApi.mapper;

import art.coinExchangeApi.coinExchangeApi.dto.BuyerDto;
import art.coinExchangeApi.coinExchangeApi.dto.SellerDto;
import art.coinExchangeApi.coinExchangeApi.entity.Buyer;
import art.coinExchangeApi.coinExchangeApi.entity.Seller;
import art.coinExchangeApi.coinExchangeApi.entity.SellerCoinInfoEntity;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapperClass {

    public static Seller mapSellerDtoToSellerJpaEntity(@NotNull SellerDto sellerDto)
    {
        List<SellerCoinInfoEntity> sellerCoinInfoEntities = new ArrayList<>();
        if (sellerDto.getSellerCoinInfoEntity() != null) {
            for (SellerCoinInfoEntity tempEntity : sellerDto.getSellerCoinInfoEntity()) {
                SellerCoinInfoEntity sellerCoinInfoEntity = new SellerCoinInfoEntity();
                sellerCoinInfoEntity.setCoin_Type(tempEntity.getCoin_Type());
                sellerCoinInfoEntity.setCoins_To_Sell(tempEntity.getCoins_To_Sell());
                // The reference to the seller will be set in the service method
                sellerCoinInfoEntities.add(sellerCoinInfoEntity);
            }
        }
        Seller seller = new Seller(
                sellerDto.getId(),
                sellerDto.getName(),
                sellerDto.getMobileNumber(),
                sellerDto.getEmail(),
                sellerDto.getLatitude(),
                sellerDto.getLongitude(),
                sellerCoinInfoEntities
        );

        for (SellerCoinInfoEntity coinInfoEntity : sellerCoinInfoEntities) {
            coinInfoEntity.setSellerInSellerCoinInfoEntity(seller);
        }

        return seller;
    }

    public static SellerDto mapSellerJpaEntityToSellerDto(@NotNull Seller seller)
    {
        List<SellerCoinInfoEntity> sellerCoinInfoEntities = new ArrayList<>();
        if (seller.getSellerCoinsDetailsList() != null) {
            for (SellerCoinInfoEntity coinInfoEntity : seller.getSellerCoinsDetailsList()) {
                SellerCoinInfoEntity tempEntity = new SellerCoinInfoEntity();
                tempEntity.setId(coinInfoEntity.getId());
                tempEntity.setCoin_Type(coinInfoEntity.getCoin_Type());
                tempEntity.setCoins_To_Sell(coinInfoEntity.getCoins_To_Sell());
                tempEntity.setSellerInSellerCoinInfoEntity(coinInfoEntity.getSellerInSellerCoinInfoEntity());
                sellerCoinInfoEntities.add(tempEntity);
            }
        }

        SellerDto sellerDto = new SellerDto(
                seller.getId(),
                seller.getName(),
                seller.getMobileNumber(),
                seller.getEmail(),
                seller.getLatitude(),
                seller.getLongitude(),
                sellerCoinInfoEntities
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
