package art.coinExchangeApi.coinExchangeApi.service.impl;

import art.coinExchangeApi.coinExchangeApi.dto.BuyerDto;
import art.coinExchangeApi.coinExchangeApi.dto.SellerDto;
import art.coinExchangeApi.coinExchangeApi.entity.Buyer;
import art.coinExchangeApi.coinExchangeApi.entity.BuyerCoinInfoEntity;
import art.coinExchangeApi.coinExchangeApi.entity.Seller;
import art.coinExchangeApi.coinExchangeApi.entity.SellerCoinInfoEntity;
import art.coinExchangeApi.coinExchangeApi.mapper.MapperClass;
import art.coinExchangeApi.coinExchangeApi.repository.BuyerRepository;
import art.coinExchangeApi.coinExchangeApi.repository.SellerRepository;
import art.coinExchangeApi.coinExchangeApi.service.CoinExchangeService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CoinExchangeServiceImpl implements CoinExchangeService {

    private static final Logger logger = LoggerFactory.getLogger(CoinExchangeService.class);

    private SellerRepository sellerRepository;

    private BuyerRepository buyerRepository;

    @Autowired
    public CoinExchangeServiceImpl(SellerRepository sellerRepository, BuyerRepository buyerRepository) {
        this.sellerRepository = sellerRepository;
        this.buyerRepository = buyerRepository;
    }


    @Override
    public SellerDto registerSeller(SellerDto sellerDto) {
        logger.info("Enter register seller");
        Seller seller = MapperClass.mapSellerDtoToSellerJpaEntity(sellerDto);
        logger.info("mapped SellerDto To SellerJpaEntity");

        logger.info(" Ensure sellerCoinInfoEntity list is initialized with valid SellerCoinInfoEntity objects");
        List<SellerCoinInfoEntity> sellerCoinInfoEntities = new ArrayList<>();
        if (sellerDto.getSellerCoinInfoEntity() != null) {
            for (SellerCoinInfoEntity tempEntity : sellerDto.getSellerCoinInfoEntity()) {
                SellerCoinInfoEntity sellerCoinInfoEntity = new SellerCoinInfoEntity();
                sellerCoinInfoEntity.setCoin_Type(tempEntity.getCoin_Type());
                sellerCoinInfoEntity.setCoins_To_Sell(tempEntity.getCoins_To_Sell());
                sellerCoinInfoEntity.setSellerInSellerCoinInfoEntity(seller); // Set the reference to Seller
                sellerCoinInfoEntities.add(sellerCoinInfoEntity);
                logger.info("Output: Coin_Type={}, Coins_To_Sell={}", tempEntity.getCoin_Type(), tempEntity.getCoins_To_Sell());

            }
        }
        seller.setSellerCoinsDetailsList(sellerCoinInfoEntities);
        logger.info("Set the initialized list to the seller entity");
        logger.info("ouput 2:", sellerCoinInfoEntities.toString());

        Seller savedSeller = sellerRepository.save(seller);
        logger.info("saving the seller entity using save method");
        SellerDto sellerDTo = MapperClass.mapSellerJpaEntityToSellerDto(savedSeller);
        logger.info("mapping back seller jpa entity to seller dto");
        logger.debug("Exit register seller");
        return sellerDTo;

    }

    @Override
    public BuyerDto registerBuyer(BuyerDto buyerDto) {
        logger.debug("enter register buyer");
        Buyer buyer = MapperClass.mapBuyerDtoToBuyerJpaEntity(buyerDto);
        Buyer savedBuyer = buyerRepository.save(buyer);
        BuyerDto buyerDTo = MapperClass.mapBuyerJpaEntityToBuyerDTo(savedBuyer);
        logger.debug("exit register buyer");
        return buyerDTo;
    }

    @Override
    @Transactional
    public List<SellerDto> findSellers(List<BuyerCoinInfoEntity> buyerCoinInfoList) {

        logger.debug("enter find sellers method");

        Map<Integer, Integer> buyerCoinsDetailsMap = buyerCoinInfoList.stream()
                .collect(Collectors.toMap(BuyerCoinInfoEntity::getCoinType, BuyerCoinInfoEntity::getCoinsToBuy));

        logger.debug("converted list to map succesffully");

        List<Seller> allSellerList = sellerRepository.findAll();

        logger.debug("successfully fetched the list of all sellers");

        List<SellerDto> filteredSellerDtoList = allSellerList.stream()
                .filter(seller -> isSellerEligible(seller, buyerCoinsDetailsMap))
                .map((seller) ->  MapperClass.mapSellerJpaEntityToSellerDto(seller))
                .collect(Collectors.toList());

        logger.debug("returning selected sellers list");

        return filteredSellerDtoList;
    }

    // Checking if a seller is eligible based on coinsToBuy >= coinsToSell for each one coin type
    private boolean isSellerEligible(Seller seller, Map<Integer, Integer> buyerCoinsDetailsMap)  {

        logger.debug("checking if seller have sufficient coins");

        for (Map.Entry<Integer, Integer> entry : buyerCoinsDetailsMap.entrySet()) {
            Integer coinType = entry.getKey();
            Integer coinsToBuyValue = entry.getValue();

            // Find matching CoinInfo in seller's list
            SellerCoinInfoEntity sellerCoinInfoEntityy = seller.getSellerCoinsDetailsList().stream()
                    .filter(sellerCoinInfoEntity -> sellerCoinInfoEntity.getCoin_Type().equals(coinType))
                    .findFirst()
                    .orElse(null);

            // Check if seller has enough coins to sell for this coin type
            if (sellerCoinInfoEntityy == null || coinsToBuyValue > sellerCoinInfoEntityy.getCoins_To_Sell()) {
                return false;
            }
        }
        return true;

    }
}
