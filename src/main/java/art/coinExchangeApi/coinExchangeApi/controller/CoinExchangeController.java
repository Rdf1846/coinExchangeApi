package art.coinExchangeApi.coinExchangeApi.controller;

import art.coinExchangeApi.coinExchangeApi.customResponseEntity.BuyerCustomResponseEntity;
import art.coinExchangeApi.coinExchangeApi.dto.BuyerDto;
import art.coinExchangeApi.coinExchangeApi.dto.SellerDto;
import art.coinExchangeApi.coinExchangeApi.entity.Buyer;
import art.coinExchangeApi.coinExchangeApi.entity.Seller;
import art.coinExchangeApi.coinExchangeApi.service.CoinExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CoinExchangeController {

    private CoinExchangeService coinExchangeService;

    public CoinExchangeController(CoinExchangeService coinExchangeService) {
        this.coinExchangeService = coinExchangeService;
    }

    @PostMapping("/sell-coins")
    public ResponseEntity<SellerDto> registerSeller(@RequestBody SellerDto sellerDto) {
        return new ResponseEntity<>(coinExchangeService.registerSeller(sellerDto), HttpStatus.CREATED);
    }

    @PostMapping("/buy-coins")
    public ResponseEntity<List<SellerDto>> registerBuyer(@RequestBody BuyerDto buyerDto) {

        coinExchangeService.registerBuyer(buyerDto);
        List<SellerDto> sellerDtoList = coinExchangeService.findSellers(buyerDto.getCoinsToBuy());
        return ResponseEntity.ok(sellerDtoList);
    }

    @PostMapping("/buy-coins/sellerList")
    public ResponseEntity<BuyerCustomResponseEntity<BuyerDto, List<SellerDto>>> registerBuyerAndFetchSellerList(@RequestBody BuyerDto buyerDto) {

       BuyerDto buyerDto1 = coinExchangeService.registerBuyer(buyerDto);
        List<SellerDto> sellerDtoList = coinExchangeService.findSellers(buyerDto.getCoinsToBuy());
        BuyerCustomResponseEntity<BuyerDto, List<SellerDto>> customResponse = new BuyerCustomResponseEntity<>(buyerDto1, sellerDtoList);
        return ResponseEntity.ok(customResponse);
    }


}
