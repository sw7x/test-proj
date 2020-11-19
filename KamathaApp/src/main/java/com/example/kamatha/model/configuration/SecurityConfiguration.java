package com.example.kamatha.model.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.example.kamatha.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
/*	@Autowired
	 private BCryptPasswordEncoder bCryptPasswordEncoder;
	 
	 @Autowired
	 private DataSource dataSource;
	 
	 private final String USERS_QUERY = "select username, password, active from user where username=?";
	 private final String ROLES_QUERY = "select username, password, active from user where username=?";

	 @Override
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	  auth.jdbcAuthentication()
	   .usersByUsernameQuery(USERS_QUERY)
	   .authoritiesByUsernameQuery(ROLES_QUERY)
	   .dataSource(dataSource)
	   .passwordEncoder(bCryptPasswordEncoder);
	 }
	 
	*/ 
 
 

    private final UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoders() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoders());
        return authProvider;
    }

    @Autowired
    public SecurityConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    } 
 
 
 @Override
 protected void configure(HttpSecurity httpSecurity) throws Exception {
     httpSecurity
             .authorizeRequests()
             .antMatchers("/", "/login", "/register")
             .permitAll()
             .anyRequest()
             .authenticated()
             .and()
             .formLogin()
             .loginPage("/login")
             .defaultSuccessUrl("/profile")
             .and()
             .logout()
             .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
             .logoutSuccessUrl("/login");
 }

 @Override
 protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
     authenticationManagerBuilder.authenticationProvider(authProvider());
     authenticationManagerBuilder.userDetailsService(userRepository::getUserByUsername);
 }
 
}