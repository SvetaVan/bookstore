package bookstore.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;


@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
        return http
                .authorizeExchange()
                //.pathMatchers("/flux/book/**").hasRole("ADMIN")
                //.pathMatchers("/flux/book/**").hasRole("USER")
                // anonymous
                .anyExchange().permitAll()
                .and()
                .formLogin()
                .and()
                .build();
    }


    @Bean
    public PasswordEncoder customPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt(4));
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
            }
        };
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        UserDetails user = User
                .withUsername("user")
                .password(BCrypt.hashpw("password",BCrypt.gensalt(4)))
                .roles("USER")
                .build();
        UserDetails admin = User
                .withUsername("admin")
                .password(BCrypt.hashpw("password",BCrypt.gensalt(4)))
                .roles("ADMIN", "USER")
                .build();

        return new MapReactiveUserDetailsService(user, admin);
    }

}
