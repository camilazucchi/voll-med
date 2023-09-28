package med.voll.api.service.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import med.voll.api.entity.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.*;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Usuario usuario) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("api.voll.med")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(expirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("api.voll.med")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            exception.printStackTrace();
            throw new RuntimeException("Token JWT inválido ou expirado", exception);
        }
    }

    private Instant expirationDate() {
        Period period = Period.ofMonths(1);

        LocalDate expirationLocalDate = LocalDate.now().plus(period);

        LocalDateTime expirationLocalDateTime = expirationLocalDate.atStartOfDay();
        return expirationLocalDateTime.toInstant(ZoneOffset.of("-03:00"));
    }

}
