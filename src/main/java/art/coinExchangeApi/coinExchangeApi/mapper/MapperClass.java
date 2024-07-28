package art.coinExchangeApi.coinExchangeApi.mapper;

import art.coinExchangeApi.coinExchangeApi.dto.BuyerDto;
import art.coinExchangeApi.coinExchangeApi.dto.SellerDto;
import art.coinExchangeApi.coinExchangeApi.dto.UserDto;
import art.coinExchangeApi.coinExchangeApi.entity.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

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

        // Setting up the seller reference in each SellerCoinInfoEntity
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
        List<BuyerCoinInfoEntity> buyerCoinInfoEntities = new ArrayList<>();
        if (buyerDto.getBuyerCoinInfoEntity() != null) {
            for (BuyerCoinInfoEntity tempEntity : buyerDto.getBuyerCoinInfoEntity()) {
                BuyerCoinInfoEntity buyerCoinInfoEntity = new BuyerCoinInfoEntity();
                buyerCoinInfoEntity.setCoin_Type(tempEntity.getCoin_Type());
                buyerCoinInfoEntity.setCoins_To_Buy(tempEntity.getCoins_To_Buy());

                // The reference to the seller will be set in the service method
                buyerCoinInfoEntities.add(buyerCoinInfoEntity);
            }
        }

        Buyer buyer = new Buyer(
                buyerDto.getId(),
                buyerDto.getName(),
                buyerDto.getMobileNumber(),
                buyerDto.getEmail(),
                buyerDto.getLatitude(),
                buyerDto.getLongitude(),
                buyerCoinInfoEntities
        );

        // Setting up the seller reference in each SellerCoinInfoEntity
        for (BuyerCoinInfoEntity coinInfoEntity : buyerCoinInfoEntities) {
            coinInfoEntity.setBuyerInBuyerCoinInfoEntity(buyer);
        }

        return buyer;
    }

    public static BuyerDto mapBuyerJpaEntityToBuyerDTo(@NotNull Buyer buyer)
    {
        List<BuyerCoinInfoEntity> buyerCoinInfoEntities = new ArrayList<>();
        if (buyer.getBuyerCoinInfoDetailsList() != null) {
            for (BuyerCoinInfoEntity coinInfoEntity : buyer.getBuyerCoinInfoDetailsList()) {
                BuyerCoinInfoEntity tempEntity = new BuyerCoinInfoEntity();
                tempEntity.setIds(coinInfoEntity.getIds());
                tempEntity.setCoin_Type(coinInfoEntity.getCoin_Type());
                tempEntity.setCoins_To_Buy(coinInfoEntity.getCoins_To_Buy());
                buyerCoinInfoEntities.add(tempEntity);
            }
        }

        BuyerDto buyerDto = new BuyerDto(
                buyer.getId(),
                buyer.getName(),
                buyer.getMobileNumber(),
                buyer.getEmail(),
                buyer.getLatitude(),
                buyer.getLongitude(),
                buyerCoinInfoEntities
        );
        return buyerDto;
    }

    public static UserDetailsEntity mapUserDtoToUserDetailsJpaEntity(@NotNull UserDto userDto)
    {
        List<CoinDenominationDetailsEntity> coinDenominationDetailsEntityList = new ArrayList<>();
        if (userDto.getCoinsDenominationList() != null) {
            for (CoinDenominationDetailsEntity tempEntity : userDto.getCoinsDenominationList()) {
                CoinDenominationDetailsEntity coinDenominationDetailsEntity = new CoinDenominationDetailsEntity();
                coinDenominationDetailsEntity.setCoin_Type(tempEntity.getCoin_Type());
                coinDenominationDetailsEntity.setNumber_of_coins(tempEntity.getNumber_of_coins());
                coinDenominationDetailsEntity.setTotal(tempEntity.getTotal());
                coinDenominationDetailsEntity.setTransaction_type_SellOrBuy(tempEntity.getTransaction_type_SellOrBuy());

                // The reference to the user will be set in the service method
                coinDenominationDetailsEntityList.add(coinDenominationDetailsEntity);
            }
        }

        UserDetailsEntity userDetailsEntity = new UserDetailsEntity(
                userDto.getId(),
                userDto.getName(),
                userDto.getUserName(),
                userDto.getMobileNumber(),
                userDto.getEmail(),
                userDto.getLatitude(),
                userDto.getLongitude(),
                userDto.getPassword(),
                coinDenominationDetailsEntityList

        );
        // Setting up the user reference in each coinsDenomintaionEntity
        for (CoinDenominationDetailsEntity coinInfoEntity : coinDenominationDetailsEntityList) {
            coinInfoEntity.setUserDetailsEntityCoins(userDetailsEntity);
        }

        return userDetailsEntity;
    }

    public static UserDto mapUserDetailsJpaEntityToUserDto(@NotNull UserDetailsEntity userDetailsEntity) {

        List<CoinDenominationDetailsEntity> coinDenominationDetailsEntityList = new ArrayList<>();
        if (userDetailsEntity.getCoinsDenominationList() != null) {
            for (CoinDenominationDetailsEntity coinInfoEntity : userDetailsEntity.getCoinsDenominationList()) {
                CoinDenominationDetailsEntity tempEntity = new CoinDenominationDetailsEntity();
                tempEntity.setId(coinInfoEntity.getId());
                tempEntity.setCoin_Type(coinInfoEntity.getCoin_Type());
                tempEntity.setNumber_of_coins(coinInfoEntity.getNumber_of_coins());
                tempEntity.setTotal(coinInfoEntity.getTotal());
                tempEntity.setTransaction_type_SellOrBuy(coinInfoEntity.getTransaction_type_SellOrBuy());
                coinDenominationDetailsEntityList.add(tempEntity);
            }
        }

        UserDto userDto = new UserDto(
                userDetailsEntity.getId(),
                userDetailsEntity.getName(),
                userDetailsEntity.getUserName(),
                userDetailsEntity.getMobileNumber(),
                userDetailsEntity.getEmail(),
                userDetailsEntity.getLatitude(),
                userDetailsEntity.getLongitude(),
                userDetailsEntity.getPassword(),
                coinDenominationDetailsEntityList
        );
        return userDto;

    }
}
