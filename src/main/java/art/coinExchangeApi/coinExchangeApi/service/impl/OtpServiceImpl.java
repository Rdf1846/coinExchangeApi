package art.coinExchangeApi.coinExchangeApi.service.impl;

import art.coinExchangeApi.coinExchangeApi.service.OtpService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Service
public class OtpServiceImpl implements OtpService {
    private Map<String, String> otpStorage = new HashMap<>();
    private SecureRandom random = new SecureRandom();

    @Override
    public String generateOtp(String key) {
        String otp = String.valueOf(100000 + random.nextInt(900000));
        otpStorage.put(key, otp);
        return otp;
    }

    @Override
    public boolean verifyOtp(String key, String otp) {
        return otp.equals(otpStorage.get(key));
    }

    @Override
    public void clearOtpAfterVerify(String key) {
        otpStorage.remove(key);
    }
}
