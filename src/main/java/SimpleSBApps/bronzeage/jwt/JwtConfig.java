package SimpleSBApps.bronzeage.jwt;

import com.google.common.net.HttpHeaders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JwtConfig {

    @Value("${app.jwt.key}")
    String key;

    @Value("${app.jwt.prefix}")
    String prefix;

    @Value("${app.jwt.expirationdays}")
    String expirationDays;

    public JwtConfig() {
    }

    public String getKey() {
        return key;
    }

    public String getPrefix() {
        return prefix;
    }

    public Integer getExpirationDays() {
        return Integer.parseInt(expirationDays);
    }

    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
}
