package pl.javarun.mywebshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.javarun.mywebshop.service.UserService;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 28.02.2020 19:34
 * *
 * @className: SecurityConfig
 * *
 * *
 ******************************************************/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final UserAuthenticationSuccessHandler successHandler;

    public SecurityConfig(UserService userService, UserAuthenticationSuccessHandler successHandler) {
        this.userService = userService;
        this.successHandler = successHandler;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/static/css/**", "/js/**", "/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .antMatchers("/h2-console/**")
                .permitAll()
                .antMatchers("/")
                .permitAll()
                .antMatchers("/types/**")
                .permitAll()
                .antMatchers("/details/**")
                .permitAll()
                .antMatchers("/rules/**")
                .permitAll()
                .antMatchers("/panels/**")
                .hasAnyAuthority("ADMIN", "SUPERUSER")
                .antMatchers("/**")
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(successHandler)
                .and()
                .exceptionHandling()
                .accessDeniedPage("/accessDenied")
                .and()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/?logout");
    }
}