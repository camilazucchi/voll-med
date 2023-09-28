package med.voll.api.service.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// A classe "SeucrityConfigurations" é uma classe de configuração Spring Security. Ela é responsável por configurar
// a segurança no nosso aplicativo Spring Boot.
@Configuration
// A anotação "@Configuration" indica que essa classe é uma classe de configuração do Spring, e o Spring irá carregar
// e processar as configurações definidas nesta classe.
@EnableWebSecurity
// A anotação "@EnableWebSecurity" ativa o suporte à segurança da web.
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
       httpSecurity
               .csrf(AbstractHttpConfigurer::disable)
               .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                       .requestMatchers(HttpMethod.POST, "/login").permitAll()
                       .anyRequest().authenticated())
               .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
               .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

       return httpSecurity.build();
    }


    @Bean
    // O "@Bean" serve para exportar uma classe para o Spring, fazendo com que ele consiga carregá-la e realize
    // a sua injeção de dependência em outras classes.
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // "PasswordEncoder" é uma interface no Spring Security usada para codificar e verificar senhas. Ele é
        // uma parte fundamental de segurança de senha, ajudando a proteger as senhas armazenadas em um aplicativo
        // de forma segura.
        return new BCryptPasswordEncoder();
        // "BCryptPasswordEncoder" é uma implementação de "PasswordEncoder", que usa o algoritmo de hash bcrypt para
        // codificar senhas. O bcrypt é uma função de hash criptograficamente segura que é amplamente considerada
        // uma das melhores práticas para armazenar senhas com segurança.
    }
}
