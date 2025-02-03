package notification.service.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
//get twilio values from app.pro 
@ConfigurationProperties(prefix="twilio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TwilioConfig {

	private String accountSid;

	private String authToken;

	private String fromPhone;
}
