package art.coinExchangeApi.coinExchangeApi.service;

public interface SmsService {
    void sendOtpSms(String mobileNumber, String otp);
}
