package art.coinExchangeApi.coinExchangeApi.service.impl;

import art.coinExchangeApi.coinExchangeApi.dto.BuyerDto;
import art.coinExchangeApi.coinExchangeApi.dto.SellerDto;
import art.coinExchangeApi.coinExchangeApi.entity.Buyer;
import art.coinExchangeApi.coinExchangeApi.entity.Seller;
import art.coinExchangeApi.coinExchangeApi.mapper.MapperClass;
import art.coinExchangeApi.coinExchangeApi.repository.BuyerRepository;
import art.coinExchangeApi.coinExchangeApi.repository.SellerRepository;
import art.coinExchangeApi.coinExchangeApi.service.CoinExchangeService;
import lombok.NoArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoinExchangeServiceImpl implements CoinExchangeService {

    private SellerRepository sellerRepository;

    private BuyerRepository buyerRepository;

    @Autowired
    public CoinExchangeServiceImpl(SellerRepository sellerRepository, BuyerRepository buyerRepository) {
        this.sellerRepository = sellerRepository;
        this.buyerRepository = buyerRepository;
    }


    @Override
    public SellerDto registerSeller(SellerDto sellerDto) {
        Seller seller = MapperClass.mapSellerDtoToSellerJpaEntity(sellerDto);
        Seller savedSeller = sellerRepository.save(seller);
        SellerDto sellerDTo = MapperClass.mapSellerJpaEntityToSellerDto(savedSeller);
        return sellerDTo;

    }

    @Override
    public BuyerDto registerBuyer(BuyerDto buyerDto) {
        Buyer buyer = MapperClass.mapBuyerDtoToBuyerJpaEntity(buyerDto);
        Buyer savedBuyer = buyerRepository.save(buyer);
        BuyerDto buyerDTo = MapperClass.mapBuyerJpaEntityToBuyerDTo(savedBuyer);
        return buyerDTo;
    }

    @Override
    public List<SellerDto> findSellers(int coinsToBuy) {
        List<Seller> allSellerList = sellerRepository.findAll();

        List<SellerDto> filteredSellerDtoList = allSellerList.stream()
                .filter(seller -> seller.getCoinsToSell() >= coinsToBuy)
                .map((seller) ->  MapperClass.mapSellerJpaEntityToSellerDto(seller))
                .collect(Collectors.toList());

        return filteredSellerDtoList;
    }
}
