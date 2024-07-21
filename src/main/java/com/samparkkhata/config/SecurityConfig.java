package com.samparkkhata.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.samparkkhata.implementations.SecurityCustomUserDetailService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // user create and login using java code with [in memory service]
    /*
     * @Bean
     * public UserDetailsService userDetailsService() {
     * UserDetails user1 = User.builder()
     * .username("admin123")
     * .password("admin123")
     * .roles("USER", "ADMIN")
     * .build();
     * 
     * InMemoryUserDetailsManager inMemoryUserDetailsManager = new
     * InMemoryUserDetailsManager(user1);
     * 
     * return inMemoryUserDetailsManager;
     * }
     */

    private final SecurityCustomUserDetailService securityCustomUserDetailService;

    public SecurityConfig(SecurityCustomUserDetailService securityCustomUserDetailService) {
        this.securityCustomUserDetailService = securityCustomUserDetailService;
    }

    // Configuration of authentication provider Spring Security
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        // User details service object:
        daoAuthenticationProvider.setUserDetailsService(this.securityCustomUserDetailService);

        // Password encoder object:
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    // Securing different fields
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // configurations
        httpSecurity.authorizeHttpRequests((authorizeHttpRequests) -> {
            // authorizeHttpRequests.requestMatchers("/home").permitAll();
            // We are authenticating all the routes stats with /user
            authorizeHttpRequests.requestMatchers("/user/**").authenticated();

            // Giving other routes [/home, /contact ...] access
            authorizeHttpRequests.anyRequest().permitAll();
        }).formLogin(formLogin -> {
            formLogin
                    .usernameParameter("userEmail")
                    .passwordParameter("userPassword")
                    .loginPage("/login")
                    .loginProcessingUrl("/authenticate")
                    .failureHandler(new AuthenticationFailureHandler() {

                        @Override
                        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                AuthenticationException exception) throws IOException, ServletException {
                            response.sendRedirect("/login?error=true");
                        }

                    }).successHandler(new AuthenticationSuccessHandler() {

                        @Override
                        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
                            response.sendRedirect("/user/dashboard");
                        }

                    });

            // .successForwardUrl("/user/dashboard")
            // .failureForwardUrl("/login?error=true");

        });

        httpSecurity.logout(logoutForm -> {
            logoutForm
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout=true");
        });

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

}
