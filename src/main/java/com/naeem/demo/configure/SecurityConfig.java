package com.naeem.demo.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {
	
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	        http
	        .csrf(csrf -> csrf.disable())
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/", "/login", "/createAccount", "/CreateAccounts","/loginAccount","/testTransfer", "/css/**").permitAll()
	                .anyRequest().authenticated()
	            )
	            .oauth2Login(oauth -> oauth
	                .loginPage("/login")                  // 🔥 custom login page
	                .defaultSuccessUrl("/dashboard", false) // 🔥 after login
	            )
	            .formLogin(form -> form
	                .loginPage("/login")
	                .permitAll()
	            )
	            .logout(logout -> logout
	            	    .logoutUrl("/logout")                     // 🔥 URL to trigger logout
	            	    .logoutSuccessUrl("/login")               // 🔥 redirect after logout
	            	    .invalidateHttpSession(true)              // 🔥 clear session
	            	    .deleteCookies("JSESSIONID")              // 🔥 remove session cookie
	            	    .permitAll()
	            	)
	            .exceptionHandling(ex -> ex
	                .authenticationEntryPoint((req, res, e) -> {
	                    res.sendRedirect("/login");      // 🔥 force login page
	                })
	            );

	        return http.build();
	    }

}
