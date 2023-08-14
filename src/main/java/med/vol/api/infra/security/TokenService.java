package med.vol.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import med.vol.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.tokenservice.secret}")
    private String secret;

    public String gerarToken(Usuario usuario) {
        try {

            var algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("spring-boot-rest-api")
                    .withSubject(usuario.getLogin())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(expirationAt())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    public String getSubject(String tokenJWT)
    {
        try {
            var algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("spring-boot-rest-api")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTVerificationException exception){

            throw new RuntimeException("Token JWT inválido ou expirado.");

        }
    }

    private Instant expirationAt() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}