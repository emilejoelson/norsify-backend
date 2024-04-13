package ma.norsysafr.norsifyApi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "rsa")
public record RsakeyConfig(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
}
