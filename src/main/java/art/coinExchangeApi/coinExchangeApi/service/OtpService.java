package art.coinExchangeApi.coinExchangeApi.service;

public interface OtpService {

    String generateOtp(String key);

    boolean verifyOtp(String key, String otp);

}
