package com.example.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringConfig {
//  @Bean
//  public UserDetailsService userDetailsService() {
//    UserDetails user = User.withDefaultPasswordEncoder()
//        .username("user")
//        .password("user1111")
//        .roles("USER")
//        .build();
//
//    UserDetails admin = User.withDefaultPasswordEncoder()
//        .username("admin")
//        .password("admin1111")
//        .roles("USER", "ADMIN")
//        .build();
//
//    return new InMemoryUserDetailsManager(user, admin);
//  }

  @Bean
  public PasswordEncoder passwordEncoder() {
  return new BCryptPasswordEncoder();
  }


  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    # RestApi 테스트 할 때만 사용
//    http.csrf(csrf -> csrf.disable());
    http.authorizeRequests()
        .requestMatchers("/login", "/account/new").permitAll()
        .requestMatchers("/products").hasRole("ADMIN")
        .anyRequest().authenticated()
        .and()
        .formLogin(login -> login.loginPage("/login")
            .usernameParameter("username")
            .passwordParameter("password")
            .defaultSuccessUrl("/home")
        )
        .exceptionHandling(exception -> exception.accessDeniedPage("/access-denied"));

    return http.build();
  }



}
