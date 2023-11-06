package com.springpj.heroesapigateway.security.jwt;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.springpj.heroesapigateway.security.constants.SecurityConstants;

import reactor.core.publisher.Mono;


@Component
public class JwtAuthorizationFilter implements WebFilter {
	
	private static final Logger log = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    private final JWTTokenProvider jwtTokenProvider;

    public JwtAuthorizationFilter(JWTTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//    	log.debug("JWTTokenProvider entry point.");
//        if (request.getMethod().equalsIgnoreCase(SecurityConstants.JWT_OPTIONS)) {
//            response.setStatus(HttpStatus.OK.value());
//        } else {
//            String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//            if (authorizationHeader == null || !authorizationHeader.startsWith(SecurityConstants.JWT_TOKEN_PREFIX)) {
//                filterChain.doFilter(request, response);
//                return;
//            }
//            String token = authorizationHeader.substring(SecurityConstants.JWT_TOKEN_PREFIX.length());
//            String username = jwtTokenProvider.getSubject(token);
//            if (jwtTokenProvider.isTokenValid(username, token) &&
//                    SecurityContextHolder.getContext().getAuthentication() == null) {
//                List<GrantedAuthority> authorities = jwtTokenProvider.getAuthorities(token);
//                Authentication authentication =
//                        jwtTokenProvider.getAuthentication(username, authorities, request);
//                
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            } else {
//                SecurityContextHolder.clearContext();
//            }
//        }
//        filterChain.doFilter(request, response);
//    }

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		
		ServerHttpRequest request = exchange.getRequest();
		ServerHttpResponse response = exchange.getResponse();
		
		log.debug("JWTTokenProvider entry point.");
        if (request.getMethod().equals(SecurityConstants.JWT_OPTIONS)) {
        	response.setStatusCode(HttpStatusCode.valueOf(200));
        } else {
            Optional<String> authorizationHeader = request.getHeaders().getOrEmpty(HttpHeaders.AUTHORIZATION)
            		.stream().filter(a -> !a.startsWith(SecurityConstants.JWT_TOKEN_PREFIX))
            		.findAny();
            if (!authorizationHeader.isPresent()) {
            	return chain.filter(exchange);
            }
            String token = authorizationHeader.get().substring(SecurityConstants.JWT_TOKEN_PREFIX.length());
            String username = jwtTokenProvider.getSubject(token);
            if (jwtTokenProvider.isTokenValid(username, token) &&
                    SecurityContextHolder.getContext().getAuthentication() == null) {
                List<GrantedAuthority> authorities = jwtTokenProvider.getAuthorities(token);
                Authentication authentication =
                        jwtTokenProvider.getAuthentication(username, authorities, request);
                
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                SecurityContextHolder.clearContext();
            }
        }
        
		return chain.filter(exchange);
	}
}
