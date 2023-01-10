package host.ankh.jeyseylearn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // 创建SecurityFilterChain的工厂函数
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
                authorizeHttpRequests((request) -> request
                        .requestMatchers("/hello").permitAll()
                        .requestMatchers("/reverse").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login.html").permitAll())
                .logout(logout -> logout.permitAll());
        return http.build();
    }
}

