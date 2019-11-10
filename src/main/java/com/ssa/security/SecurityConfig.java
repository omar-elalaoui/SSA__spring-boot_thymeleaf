package com.ssa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    DataSource dataSource;
    @Autowired
    AuthenticationSuccessHandler loginSuccessHandler;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationSuccessHandler LoginSuccessHandler() {
        return new LoginSuccessHandler();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username as principal, pwd as credentials, active from user where username=?")
                .authoritiesByUsernameQuery("select username as principal, role as role from user_role where username=?")
                .rolePrefix("ROLE_")
                .passwordEncoder(  new BCryptPasswordEncoder() );
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login")
        .successHandler(loginSuccessHandler);
        http.authorizeRequests().antMatchers("/", "/dash/**", "/projets/**", "/user/profile_ov", "/documents/**").hasRole("USER");
        http.authorizeRequests().antMatchers( "/users/**", "/notifs/**", "/log/**", "/user/profile_param/**").hasRole("ADMIN");
    }
    
    
}
