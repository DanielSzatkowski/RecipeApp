package pl.umk.mat.danielsz.recipeapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.umk.mat.danielsz.recipeapp.security.SecurityConst;
import pl.umk.mat.danielsz.recipeapp.security.filters.JwtUserAuthenticationFilter;
import pl.umk.mat.danielsz.recipeapp.security.filters.JwtUserAuthorizationFilter;
import pl.umk.mat.danielsz.recipeapp.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final SecurityConst securityConst;
    private final UserService userService;

    @Autowired
    public SecurityConfiguration(UserDetailsService userDetailsService, SecurityConst securityConst,
                                 UserService userService){

        this.userDetailsService = userDetailsService;
        this.securityConst = securityConst;
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/api/**").permitAll() //TODO: change security option na authenticated()
            .antMatchers("/h2").permitAll()     //TODO: return to antMatchers
            .antMatchers("/actuator/**").hasRole("ADMIN")
                .and()
            .httpBasic()
                .and()
            .cors()
                .and()
            .csrf().disable()   //TODO: csrf enable
            .addFilter(new JwtUserAuthenticationFilter(authenticationManager(), securityConst))
            .addFilter(new JwtUserAuthorizationFilter(authenticationManager(), securityConst, userService))
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
