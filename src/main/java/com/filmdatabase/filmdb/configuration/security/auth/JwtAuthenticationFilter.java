package com.filmdatabase.filmdb.configuration.security.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.filmdatabase.filmdb.api.service.UserService;
import com.filmdatabase.filmdb.application.model.user.role.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static com.filmdatabase.filmdb.configuration.security.auth.SecurityConstants.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    private final List<String> allowedOrigins = Arrays.asList("http://localhost:4200");

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        if (logger.isDebugEnabled()) {
            logger.debug("Request method " + req.getServletPath());
        }

        try {
            com.filmdatabase.filmdb.application.model.user.dao.User creds = new ObjectMapper()
                    .readValue(req.getInputStream(), com.filmdatabase.filmdb.application.model.user.dao.User.class);

            List<GrantedAuthority> list = new ArrayList<>();

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
//                            (Collection<? extends GrantedAuthority>) creds.getRoles())
                            list)
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
        res.setHeader(MAX_AGE, MAX_AGE_TIME);

        // Access-Control-Allow-Credentials
        res.setHeader(ALLOW_CREDENTIALS, ALLOW_CREDENTIALS_VALUE);

        // Access-Control-Allow-Methods
        res.setHeader(METHODS_ALLOWED, "POST, GET, OPTIONS, DELETE");

        String a = SecurityConstants.getGeneratedHeaders();

        // Access-Control-Allow-Headers
        //todo sprawdzi czy authorization z malej jest potrzebne
        res.setHeader(HEADERS_ALLOWED,
                "Origin, Authorization, ROLES, X-Requested-By, X-Requested-With, Content-Type, Accept, " + "X-CSRF-TOKEN");
        res.setHeader("Access-Control-Expose-Headers", HEADER_STRING + ", ROLES");
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        //res.addHeader("ROLES", String.valueOf(((User) auth.getPrincipal()).getAuthorities()));
        List<GrantedAuthority> roles = new ArrayList<>();
        GrantedAuthority test = new SimpleGrantedAuthority("ROLE_USER");
        GrantedAuthority test2 = new SimpleGrantedAuthority("ROLE_ADMIN");
        roles.add(test);
        roles.add(test2);
        res.addHeader("ROLES", String.valueOf(roles));
    }


}