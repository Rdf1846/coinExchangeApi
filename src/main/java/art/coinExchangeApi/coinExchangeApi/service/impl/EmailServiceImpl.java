package art.coinExchangeApi.coinExchangeApi.service.impl;

import art.coinExchangeApi.coinExchangeApi.detailsClass.OtpDetails;
import org.springframework.beans.factory.annotation.Autowired;
import art.coinExchangeApi.coinExchangeApi.service.EmailService;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;


    @Override
    public void sendOtpEmail(String email, String otp) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("OTP Code to verify for coin exchange application");
        message.setText("Note: Otp will expire in 5mins! Your OTP code is: " + otp);
        mailSender.send(message);

    }
}
