package art.coinExchangeApi.coinExchangeApi.service.impl;

import art.coinExchangeApi.coinExchangeApi.service.SmsService;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SmsServiceImpl implements SmsService {
    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String senderPhoneNumber;

    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
    }

    @Override
    public void sendOtpSms(String mobileNumber, String otp) {
            Message.creator(
                    new PhoneNumber(mobileNumber),
                    new PhoneNumber(senderPhoneNumber),
                    "Your OTP code is: " + otp
            ).create();
        }

    }

