package com.book.store.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private JwtFilter jwtFilter;

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http.csrf(customizer -> customizer.disable())
				.authorizeHttpRequests(request -> request.requestMatchers("/api/users/login").permitAll()
						.anyRequest().authenticated())

				// Configure form login
				.formLogin(formLogin -> formLogin.loginPage("/login").usernameParameter("username")
						.passwordParameter("password").loginProcessingUrl("/process-login").defaultSuccessUrl("/home")
						.failureUrl("/login?error=true").permitAll())

				// Configure logout
				.logout(logout -> logout.logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true)
						.deleteCookies("JSESSIONID").permitAll())

				// Configure session management
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
						.maximumSessions(1).expiredUrl("/login?expired=true"))

				// Configure exception handling
				.exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedPage("/access-denied"))

				// Add custom JWT filter before UsernamePasswordAuthenticationFilter
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

				// Configure HTTP Basic authentication
				.httpBasic(Customizer.withDefaults())

				.build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		provider.setUserDetailsService(userDetailsService);

		return provider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();

	}

}