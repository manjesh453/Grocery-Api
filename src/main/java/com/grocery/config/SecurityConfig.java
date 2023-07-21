package com.grocery.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.grocery.security.CustomerUserDetailService;
import com.grocery.security.JwtAuthenticationEntryPoint;
import com.grocery.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Autowired
	private CustomerUserDetailService customerUserDetailService;
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
		http
       .csrf(csrf->csrf.disable())
       .authorizeHttpRequests(auth->auth.requestMatchers("/api/auth/**").permitAll()    		  
    		   .requestMatchers(HttpMethod.GET).permitAll()
    		   .anyRequest().authenticated())
       .exceptionHandling(exception->exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))
       .sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
       

		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		http.authenticationProvider(daoAuthenticationProvider());
		DefaultSecurityFilterChain defaultSecurityFilterChain=http.build();
		return defaultSecurityFilterChain;
	}
   
   
   public void configure(AuthenticationManagerBuilder auth)throws Exception{
	   auth.userDetailsService(this.customerUserDetailService).passwordEncoder(passwordEncoder());
   }
   
   @Bean
    public AuthenticationManager aunthenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
    	return configuration.getAuthenticationManager();
    }
   @Bean
   public DaoAuthenticationProvider daoAuthenticationProvider() {
	   DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
	   provider.setUserDetailsService(this.customerUserDetailService);
	   provider.setPasswordEncoder(passwordEncoder());
	   return provider;
	   
   }
   @Bean
   public PasswordEncoder passwordEncoder() {
	   return new BCryptPasswordEncoder();
   }
   
   @Bean
   public FilterRegistrationBean<CorsFilter> coresFilter() {
	   UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
       CorsConfiguration corsConfiguration = new CorsConfiguration();
       corsConfiguration.setAllowCredentials(true);
       corsConfiguration.addAllowedOriginPattern("*");
       corsConfiguration.addAllowedHeader("Authorization");
       corsConfiguration.addAllowedHeader("Content-Type");
       corsConfiguration.addAllowedMethod("OPTIONS");
       corsConfiguration.addAllowedHeader("Accept");
       corsConfiguration.addAllowedMethod("POST");
       corsConfiguration.addAllowedMethod("PUT");
       corsConfiguration.addAllowedMethod("GET");
       corsConfiguration.addAllowedMethod("DELETE");
       corsConfiguration.setMaxAge(3600L);

       source.registerCorsConfiguration("/**", corsConfiguration);
       FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
       bean.setOrder(0);
       return bean;
   }
}
