package com.springproject.config;

import com.springproject.handler.LoginSuccessHandler;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @SneakyThrows
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http){

        http.csrf(csrf -> csrf.disable());

        http.formLogin(fl -> fl.loginPage("/login").loginProcessingUrl("/login")
                        .usernameParameter("j_email")
                        .passwordParameter("j_password")
                                .successHandler(new LoginSuccessHandler())
                        .permitAll());

        http.
                        logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll());



        http.
                        authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/registration").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user").permitAll().anyRequest().authenticated());

        return http.build();
    }

}
