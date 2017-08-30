package com.template.config;

import com.template.handlers.CustomAuthenticationSuccessHandler;
import com.template.handlers.LogoutSuccessHandler;
import com.template.security.LoadUserSpecificDataFormLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by: Sergey Volokh
 * Date: 5/17/2016
 * Time: 2:53 PM
 * Project: Spring MVC
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private AuthenticationSuccessHandler authenticationSuccessHandler;
//
//    @Autowired
//    private AuthenticationFailureHandler authenticationFailureHandler;


    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    public void configure(WebSecurity web) throws Exception {
        //Spring Security ignores request to static resources such as CSS or JS files.
        web.ignoring().antMatchers("/static/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(
                            "/register",
                            "/logins"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/logins")
    //                    .successHandler(authenticationSuccessHandler("/"))
    //                    .successHandler(authenticationSuccessHandler)
    //                    .failureHandler(authenticationFailureHandler)
                .permitAll().and()
                .logout().permitAll()
                .and()
                .rememberMe()
                    .rememberMeParameter("rememberMe")
                    .rememberMeCookieName("rememberStarProjectCookie");
    }

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }


    @Bean
    public RememberMeServices rememberMeServices() {
        TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices("appSecretKey", userDetailsService());
        rememberMeServices.setCookieName("cookieName");
        rememberMeServices.setParameter("rememberMe");
        return rememberMeServices;
    }

//    @Bean
//    public AuthenticationSuccessHandler authenticationSuccessHandler(String successLoginUrl) {
//        return new CustomAuthenticationSuccessHandler(successLoginUrl);
//    }

//    @Bean(name = "myAuthenticationManager")
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        return new LoadUserSpecificDataFormLoginService();
//    }
//
//    @Bean
//    public LogoutSuccessHandler logoutSuccessHandler() {
//        return new LogoutSuccessHandler();
//    }
//

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("user").password("123").roles("USER");
//    }


}
