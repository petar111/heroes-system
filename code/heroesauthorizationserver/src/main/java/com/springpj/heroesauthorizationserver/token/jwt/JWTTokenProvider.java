package com.springpj.heroesauthorizationserver.token.jwt;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import java.util.Date;

import com.springpj.heroesauthorizationserver.constants.SecurityConstants;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.springpj.heroesauthorizationserver.model.user.UserPrincipal;

@Component
public class JWTTokenProvider {

    public String generateJwtToken(UserPrincipal userPrincipal) {
        String[] claims = getClaimsFromUser(userPrincipal);
        return JWT.create()
                .withIssuer(SecurityConstants.JWT_ISSUER)
                .withAudience(SecurityConstants.JWT_AUDIENCE)
                .withIssuedAt(new Date())
                .withSubject(userPrincipal.getUsername() + SecurityConstants.JWT_SUBJECT_DELIMITER + userPrincipal.getUser().getId())
          
                .withArrayClaim(SecurityConstants.JWT_AUTHORITIES, claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(HMAC512(SecurityConstants.JWT_SECRET_KEY.getBytes()));
    }

    private boolean isTokenExpired(JWTVerifier verifier, String token) {
        Date expiration = verifier.verify(token).getExpiresAt();
        return expiration.before(new Date());
    }

    private JWTVerifier getJWTVerifier() {
        JWTVerifier verifier;
        try {
            Algorithm algorithm = HMAC512(SecurityConstants.JWT_SECRET_KEY);
            verifier = JWT.require(algorithm).withIssuer(SecurityConstants.JWT_ISSUER).build();
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException(SecurityConstants.JWT_VERIFIER_ERROR);
        }
        return verifier;
    }

    private String[] getClaimsFromUser(UserPrincipal userPrincipal) {
        return userPrincipal.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toArray(String[]::new);
    }
}
