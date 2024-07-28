package art.coinExchangeApi.coinExchangeApi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import art.coinExchangeApi.coinExchangeApi.service.EmailService;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendOtpEmail(String email, String otp) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("OTP Code to verify for coin exchange application");
        message.setText("Your OTP code is: " + otp);
        mailSender.send(message);

    }
}
