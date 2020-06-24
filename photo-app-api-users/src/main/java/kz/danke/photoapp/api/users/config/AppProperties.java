package kz.danke.photoapp.api.users.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
@Getter
@Setter
@NoArgsConstructor
public class AppProperties {

    private long tokenExpiration;
    private String tokenSecret;
    private String loginUrlPath;

    private final Gateway gateway = new Gateway();

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Gateway {

        private String ip;
    }
}
