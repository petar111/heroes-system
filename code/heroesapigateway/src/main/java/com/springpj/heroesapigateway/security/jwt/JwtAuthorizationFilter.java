package com.springpj.heroesapigateway.security.jwt;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.springpj.heroesapigateway.security.constants.SecurityConstants;

import reactor.core.publisher.Mono;

import static com.springpj.heroesapigateway.security.constants.SecurityConstants.JWT_SUBJECT_DELIMITER;


@Component
public class JwtAuthorizationFilter implements WebFilter {
	
	private static final Logger log = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    private final JWTTokenProvider jwtTokenProvider;

    public JwtAuthorizationFilter(JWTTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

		log.info("JwtAuthorizationFilter - START");
		ServerHttpRequest request = exchange.getRequest();

        Optional<String> authorizationHeader = request.getHeaders().getOrEmpty(HttpHeaders.AUTHORIZATION)
                .stream().filter(a -> a.startsWith(SecurityConstants.JWT_TOKEN_PREFIX))
                .findAny();

        if (authorizationHeader.isEmpty()) {
            log.info("JwtAuthorizationFilter - DONE - Authorization header empty");
            return chain.filter(exchange);
        }

        String token = authorizationHeader.get().substring(SecurityConstants.JWT_TOKEN_PREFIX.length());

        String username = extractUsernameFromToken(token);

        if (jwtTokenProvider.isTokenValid(username, token) &&
                SecurityContextHolder.getContext().getAuthentication() == null) {
            List<GrantedAuthority> authorities = jwtTokenProvider.getAuthorities(token);
            Authentication authentication =
                    jwtTokenProvider.getAuthentication(username, authorities, request);

            log.info("JwtAuthorizationFilter - DONE");
            return chain.filter(exchange).contextWrite(ctx -> ReactiveSecurityContextHolder.withAuthentication(authentication));
        } else {
            log.info("JwtAuthorizationFilter - DONE - Failed authentication");
            return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.clearContext());
        }
	}

    private String extractUsernameFromToken(String token){
        return jwtTokenProvider.getSubject(token)
                                    .split(JWT_SUBJECT_DELIMITER)[0];
    }
}
