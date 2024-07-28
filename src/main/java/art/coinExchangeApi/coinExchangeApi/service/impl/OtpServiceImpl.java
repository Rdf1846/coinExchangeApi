package art.coinExchangeApi.coinExchangeApi.service.impl;

import art.coinExchangeApi.coinExchangeApi.detailsClass.OtpDetails;
import art.coinExchangeApi.coinExchangeApi.service.OtpService;
import art.coinExchangeApi.coinExchangeApi.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class OtpServiceImpl implements OtpService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SmsService smsService;

    private Map<String, OtpDetails> otpStorage = new HashMap<>();
    private SecureRandom random = new SecureRandom();



    @Override
    public String generateOtp(String key) {
        String otp = String.format("%06d", random.nextInt(1000000));
        otpStorage.put(key, new OtpDetails(otp, LocalDateTime.now()));
        return otp;
    }

    @Override
    public boolean verifyOtp(String key, String otp) {
        OtpDetails otpDetails = otpStorage.get(key);
        if(otpDetails!=null && otpDetails.getOtp().equals(otp))
        {
            if(isOtpExpired(otpDetails.getGeneratedTime()))
            {
                otpStorage.remove(key);
                return false;
            }
            otpStorage.remove(key);
            return true;

        }
        return false;
    }

    private boolean isOtpExpired(LocalDateTime generatedTime)
    {
        return generatedTime.plusMinutes(5).isBefore(LocalDateTime.now());
    }

}
