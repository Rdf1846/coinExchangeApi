package art.coinExchangeApi.coinExchangeApi.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests.requestMatchers("/**").permitAll()
                                .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable());
        /* Here we have used crf.disable() method to allow all our calls to our endpoint, bcoz we have included
        okta and spring security in our pom.xml, but we have not done anything to handle them till now.
        we will update this securityConfiguration post implementation of okta security. */

        return http.build();
    }
}
