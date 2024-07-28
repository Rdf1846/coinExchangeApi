package art.coinExchangeApi.coinExchangeApi.detailsClass;

import java.time.LocalDateTime;

public class OtpDetails {
    private String otp;
    private LocalDateTime generatedTime;

    public OtpDetails(String otp, LocalDateTime generatedTime) {
        this.otp = otp;
        this.generatedTime = generatedTime;
    }

    public String getOtp() {
        return otp;
    }

    public LocalDateTime getGeneratedTime() {
        return generatedTime;
    }
}
