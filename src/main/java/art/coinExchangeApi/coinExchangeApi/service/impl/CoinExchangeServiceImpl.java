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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        logger.info("Entered register seller");
        Seller seller = MapperClass.mapSellerDtoToSellerJpaEntity(sellerDto);
        logger.info("mapped SellerDto To SellerJpaEntity");

        logger.info(" Ensuring that sellerCoinInfoEntity list is initialized with valid SellerCoinInfoEntity objects");
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

        Seller savedSeller = sellerRepository.save(seller);
        logger.info("saving the seller entity using save method");
        SellerDto sellerDTo = MapperClass.mapSellerJpaEntityToSellerDto(savedSeller);
        logger.info("mapping back seller jpa entity to seller dto");
        logger.info("Exit register seller");
        return sellerDTo;

    }

    @Override
    public BuyerDto registerBuyer(BuyerDto buyerDto) {
        logger.debug("Entered register buyer");
        Buyer buyer = MapperClass.mapBuyerDtoToBuyerJpaEntity(buyerDto);
        logger.info("mapped BuyerDto To BuyerJpaEntity");

        logger.info(" Ensuring that buyerCoinInfoEntity list is initialized with valid BuyerCoinInfoEntity objects");
        List<BuyerCoinInfoEntity> buyerCoinInfoEntities = new ArrayList<>();
        if (buyerDto.getBuyerCoinInfoEntity() != null) {
            for (BuyerCoinInfoEntity tempEntity : buyerDto.getBuyerCoinInfoEntity()) {
                BuyerCoinInfoEntity buyerCoinInfoEntity = new BuyerCoinInfoEntity();
                buyerCoinInfoEntity.setCoin_Type(tempEntity.getCoin_Type());
                buyerCoinInfoEntity.setCoins_To_Buy(tempEntity.getCoins_To_Buy());
                buyerCoinInfoEntity.setBuyerInBuyerCoinInfoEntity(buyer); // Set the reference to Seller
                buyerCoinInfoEntities.add(buyerCoinInfoEntity);
                logger.info("Output: Coin_Type={}, Coins_To_Buy={}", tempEntity.getCoin_Type(), tempEntity.getCoins_To_Buy());
            }
        }
        buyer.setBuyerCoinInfoDetailsList(buyerCoinInfoEntities);
        logger.info("Set the initialized list to the buyer entity");

        Buyer savedBuyer = buyerRepository.save(buyer);
        logger.info("saving the buyer entity using save method");
        BuyerDto buyerDTo = MapperClass.mapBuyerJpaEntityToBuyerDTo(savedBuyer);
        logger.info("mapping back buyer jpa entity to buyer dto");
        logger.debug("exit register buyer");
        return buyerDTo;
    }

    @Override
    public List<SellerDto> findSellers(List<BuyerCoinInfoEntity> buyerCoinInfoList) {

        logger.info("Entered find sellers method to check if seller is eligible");

        List<BuyerCoinInfoEntity> tempList = buyerCoinInfoList;
        
        logger.info("converting buyerCoinInfo list to Map");
//        Map<Integer, Integer> buyerCoinsDetailsMap = buyerCoinInfoList.stream()
//                .collect(Collectors.toMap(BuyerCoinInfoEntity::getCoin_Type, BuyerCoinInfoEntity::getCoins_To_Buy));
        logger.info("converted list to map successfully, using stream function");

        List<Seller> allSellerList = sellerRepository.findAll();
        logger.debug("successfully fetched the list of all sellers in the database");

        logger.info("fetching the eligible list of sellers who have either equal or greater coins to sell than buyer wants");
        
        List<SellerDto> filteredSellerDtoList = fetchReqdSeller(tempList, allSellerList);


        logger.info("returning selected sellers list");

        return filteredSellerDtoList;
    }


	/**
	 * @param tempList
	 * @param allSellerList
	 * @return
	 */
	private List<SellerDto> fetchReqdSeller(List<BuyerCoinInfoEntity> tempList, List<Seller> allSellerList) {
		return allSellerList.stream().
										        	filter(seller -> seller.getSellerCoinsDetailsList().stream()
										        			.anyMatch(sellerCoinInfoDetails -> tempList.stream()
										        					.anyMatch(req -> sellerCoinInfoDetails.getCoin_Type().equals(req.getCoin_Type()) 
										        							&& sellerCoinInfoDetails.getCoins_To_Sell() >= req.getCoins_To_Buy())))
										        					.map((seller) ->  MapperClass.mapSellerJpaEntityToSellerDto(seller))
										        							.collect(Collectors.toList());
	}


}
