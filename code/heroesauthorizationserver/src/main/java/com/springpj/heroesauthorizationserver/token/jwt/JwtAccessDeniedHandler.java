package com.springpj.heroesauthorizationserver.token.jwt;


import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springpj.heroesauthorizationserver.errorhandler.exception.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException {
        ErrorResponse httpErrorResponse = 
        		ErrorResponse.builder()
        			.withStatus(UNAUTHORIZED)
        			.withMessage("Unauthorized")
        			.withDetailedMessage("Unauthorized")
        			.withStackTrace("").build();

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(FORBIDDEN.value());
        OutputStream outputStream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(outputStream, httpErrorResponse);
        outputStream.flush();

    }
}
