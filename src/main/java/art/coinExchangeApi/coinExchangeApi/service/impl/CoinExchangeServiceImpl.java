package art.coinExchangeApi.coinExchangeApi.service.impl;

import art.coinExchangeApi.coinExchangeApi.dto.BuyerDto;
import art.coinExchangeApi.coinExchangeApi.dto.SellerDto;
import art.coinExchangeApi.coinExchangeApi.dto.UserDto;
import art.coinExchangeApi.coinExchangeApi.entity.*;
import art.coinExchangeApi.coinExchangeApi.mapper.MapperClass;
import art.coinExchangeApi.coinExchangeApi.repository.BuyerRepository;
import art.coinExchangeApi.coinExchangeApi.repository.SellerRepository;
import art.coinExchangeApi.coinExchangeApi.repository.UserDetailsRepository;
import art.coinExchangeApi.coinExchangeApi.service.CoinExchangeService;
import jakarta.validation.constraints.Null;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Optionals;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoinExchangeServiceImpl implements CoinExchangeService {

    private static final Logger logger = LoggerFactory.getLogger(CoinExchangeService.class);

    private SellerRepository sellerRepository;

    private BuyerRepository buyerRepository;

    private UserDetailsRepository userDetailsRepository;

    @Autowired
    public CoinExchangeServiceImpl(SellerRepository sellerRepository, BuyerRepository buyerRepository, UserDetailsRepository userDetailsRepository) {
        this.sellerRepository = sellerRepository;
        this.buyerRepository = buyerRepository;
        this.userDetailsRepository = userDetailsRepository;
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



//New code from here

    @Override
    public UserDto registerUserDetails(UserDto userDto) {
        UserDetailsEntity userDetailsEntity = MapperClass.mapUserDtoToUserDetailsJpaEntity(userDto);

        List<CoinDenominationDetailsEntity> coinDenominationDetailsEntityList = new ArrayList<>();
        if (userDto.getCoinsDenominationList() != null) {
            for (CoinDenominationDetailsEntity tempEntity : userDto.getCoinsDenominationList()) {
                CoinDenominationDetailsEntity coinDenominationDetailsEntity = new CoinDenominationDetailsEntity();
                coinDenominationDetailsEntity.setCoin_Type(tempEntity.getCoin_Type());
                coinDenominationDetailsEntity.setNumber_of_coins(tempEntity.getNumber_of_coins());
                coinDenominationDetailsEntity.setTotal(tempEntity.getTotal());
                coinDenominationDetailsEntity.setTransaction_type_SellOrBuy(tempEntity.getTransaction_type_SellOrBuy());
                coinDenominationDetailsEntity.setUserDetailsEntityCoins(userDetailsEntity); // Set the reference to Seller
                coinDenominationDetailsEntityList.add(coinDenominationDetailsEntity);
                logger.info("Output: Coin_Type={}, Coins_To_Sell={}, total={}, transaction_type={}" , tempEntity.getCoin_Type(), tempEntity.getNumber_of_coins(), tempEntity.getTotal(), tempEntity.getTransaction_type_SellOrBuy());
            }
        }
        userDetailsEntity.setCoinsDenominationList(coinDenominationDetailsEntityList);
        logger.info("Set the initialized list to the userDetails entity");

        UserDetailsEntity savedUserDetailsEntity = userDetailsRepository.save(userDetailsEntity);
        logger.info("saving the user entity using save method");
        UserDto resultUserDto = MapperClass.mapUserDetailsJpaEntityToUserDto(savedUserDetailsEntity);
        logger.info("mapping back user jpa entity to user dto");
        logger.info("Exit register user");


        return resultUserDto;
    }

    @Override
    public boolean verifyUserPassword(Map<String, String> request) {
        String userName = request.get("userName");
        String email = request.get("email");
        String mobileNumber = request.get("mobileNumber");
        String password = request.get("password");
        Optional<UserDetailsEntity> userDetailsEntityOptional = Optional.empty();
        if(userName != null) {
            userDetailsEntityOptional = userDetailsRepository.findByUserName(userName);
        } else if (email != null) {
            userDetailsEntityOptional = userDetailsRepository.findByEmail(email);
        } else if (mobileNumber != null) {
            userDetailsEntityOptional = userDetailsRepository.findByMobileNumber(mobileNumber);
        }
        else {
            return false;
        }
        if(userDetailsEntityOptional.isPresent())
        {
            UserDetailsEntity userDetailsEntity = userDetailsEntityOptional.get();
            return password.equals(userDetailsEntity.getPassword());
        }

        return false;
    }

    @Override
    public String updateInUserDetailsEntity(String userName, UserDto userDto) {
        UserDetailsEntity userDetailsEntity = userDetailsRepository
                .findByUserName(userName)
                .orElseThrow( () -> new RuntimeException("User does not exist"));

        if(userDto.getUserName() != null) {
            userDetailsEntity.setUserName(userDto.getUserName());
        }
        if(userDto.getPassword() != null) {
            userDetailsEntity.setPassword(userDto.getPassword());
        }if(userDto.getName() != null) {
            userDetailsEntity.setName(userDto.getName());
        }if(userDto.getEmail() != null) {
            userDetailsEntity.setEmail(userDto.getEmail());
        }if(userDto.getMobileNumber() != null) {
            userDetailsEntity.setMobileNumber(userDto.getMobileNumber());
        }if(userDto.getLongitude() != null) {
            userDetailsEntity.setLongitude(userDto.getLongitude());
        }if(userDto.getLatitude() != null) {
            userDetailsEntity.setLatitude(userDto.getLongitude());
        }if(userDto.getCoinsDenominationList() != null) {
            userDetailsEntity.setCoinsDenominationList(userDto.getCoinsDenominationList());
        }

        UserDetailsEntity savedUserDetailsEntity =  userDetailsRepository.save(userDetailsEntity);
        return "success";
    }

    @Override
    public UserDto getUserDetailsByUserName(String userName) {
        UserDetailsEntity userDetailsEntity = userDetailsRepository
                .findByUserName(userName)
                .orElseThrow( () -> new RuntimeException("User does not exist"));

        UserDto userDto = MapperClass.mapUserDetailsJpaEntityToUserDto(userDetailsEntity);

        return userDto;
    }

}



