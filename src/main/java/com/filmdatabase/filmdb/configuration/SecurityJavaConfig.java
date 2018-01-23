package com.filmdatabase.filmdb.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("ram").password("ram123").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("ravan").password("ravan123").roles("USER");
        auth.inMemoryAuthentication().withUser("kans").password("kans123").roles("PREMIUM");
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors();
//        http.authorizeRequests()
//                .antMatchers("/").permitAll();

        http.authorizeRequests()
                .antMatchers("/film/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/films/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
                .and().formLogin();

        //todo not working for now
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/film/").authenticated()
                .and().formLogin();
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/film/**").authenticated()
                .and().formLogin();
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/film/**").authenticated()
                .and().formLogin();
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("https://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}