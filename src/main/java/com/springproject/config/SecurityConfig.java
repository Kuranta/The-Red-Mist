package com.springproject.config;

import com.springproject.handler.LoginSuccessHandler;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final UserDetailsService userDetailService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        return daoAuthenticationProvider;
    }

    @SneakyThrows
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http){

        http
                .formLogin(form -> form.loginPage("/login")
                .successHandler(new LoginSuccessHandler())
                        .loginProcessingUrl("/login")
                        .usernameParameter("j_email")
                        .passwordParameter("j_password")
                        .permitAll());

        http.
                logout(logout -> logout
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout"));

        http.
                authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/login").anonymous()
                        .requestMatchers("/user").permitAll().anyRequest().authenticated());


        return http.build();
    }

//
    public SecurityConfig(@Qualifier("userDetailServiceImpl") UserDetailsService userDetailService) {
        this.userDetailService = userDetailService;

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
