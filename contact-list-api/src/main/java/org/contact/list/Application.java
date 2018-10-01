package org.contact.list;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@SpringBootApplication(
        scanBasePackages = {
                "org.contact.list.controller",
                "org.contact.list.service"
        }
)
public class Application {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    @Configuration
    protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Value("${app.spring.security.user}")
        String username;

        @Value("${app.spring.security.password}")
        String password;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
                http
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/").permitAll()
                    //.anyRequest().authenticated()
                    .anyRequest().permitAll()
                    .and()
                    .httpBasic();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth)
                throws Exception {
            auth
                    .inMemoryAuthentication()
                    .withUser(username)
                    .password("{noop}" + password)
                    .roles("USER");
        }
    }

}
