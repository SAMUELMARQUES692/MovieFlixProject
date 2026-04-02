package dev.com.movieflix.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import dev.com.movieflix.model.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TokenService {

    @Value("${movieflix.security.secret}")
    private String secret;

    public String generateToken(UserModel user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("UserId", user.getId())
                .withClaim("name", user.getName())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now())
                .withIssuer("API MovieFlix")
                .sign(algorithm);
    }

}
