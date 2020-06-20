package pl.umk.mat.danielsz.recipeapp.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "security")
@Getter @Setter
public class SecurityConst {

    private Long expirationTime;

    private String secret;

    private String header;

    private String tokenPrefix;
}
