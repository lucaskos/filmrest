package com.filmdatabase.filmdb.configuration.security.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static com.filmdatabase.filmdb.configuration.security.SecurityConstants.*;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    private final List<String> allowedOrigins = Arrays.asList("http://localhost:4200");
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            com.filmdatabase.filmdb.application.model.user.dao.User creds = new ObjectMapper()
                    .readValue(req.getInputStream(), com.filmdatabase.filmdb.application.model.user.dao.User.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            (Collection<? extends GrantedAuthority>) creds.getRoles())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String token = Jwts.builder()
                .setSubject(((User) auth.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();
        String origin = req.getHeader("Origin");
        res.setHeader("Access-Control-Allow-Headers", HEADER_STRING + ", " + "Content-Type, Accept, X-Requested-With, remember-me");
        res.setHeader("Access-Control-Allow-Origin", allowedOrigins.contains(origin) ? origin : "");
        res.setHeader("Vary", "Origin");

        // Access-Control-Max-Age
        res.setHeader("Access-Control-Max-Age", "3600");

        // Access-Control-Allow-Credentials
        res.setHeader("Access-Control-Allow-Credentials", "true");

        // Access-Control-Allow-Methods
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");

        // Access-Control-Allow-Headers
        res.setHeader("Access-Control-Allow-Headers",
                "Origin, Authorization, X-Requested-By, X-Requested-With, Content-Type, Accept, " + "X-CSRF-TOKEN");

        res.setHeader("Access-Control-Expose-Headers", HEADER_STRING);
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }
}