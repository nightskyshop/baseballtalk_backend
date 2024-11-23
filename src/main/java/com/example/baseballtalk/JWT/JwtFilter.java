package com.example.baseballtalk.JWT;

import com.example.baseballtalk.DTO.TokenDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String REFRESH_AUTHORIZATION_HEADER = "RefreshToken";
    public static final String BEARER_PREFIX = "Bearer ";
    private final TokenProvider tokenProvider;

    private final ObjectMapper objectMapper = new ObjectMapper();


    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private String resolveRefreshToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(REFRESH_AUTHORIZATION_HEADER);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = resolveToken(request);
        String refresh = resolveRefreshToken(request);

        System.out.println("refreshToken: " + refresh);

        if (StringUtils.hasText(refresh) && tokenProvider.validateRefreshToken(refresh)) {
            TokenDTO tokenDTO = tokenProvider.getAccessTokenFromRefreshToken(refresh);
            response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            writeTokenResponse(response, tokenDTO);
            return;
        }

//        System.out.println("JWT: " + jwt);
//        System.out.println(StringUtils.hasText(jwt));

        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private void writeTokenResponse(HttpServletResponse response, TokenDTO tokenDTO) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        String json = objectMapper.writeValueAsString(tokenDTO);
        response.getWriter().write(json);
    }
}
