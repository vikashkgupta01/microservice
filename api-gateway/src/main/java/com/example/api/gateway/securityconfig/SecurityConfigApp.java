package com.example.api.gateway.securityconfig;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigApp {

    static {
        System.out.println("SecurityConfigApp.class is loading...");
    }

    public SecurityConfigApp() {
        System.out.println("SecurityConfigApp.SecurityConfigApp()");
    }

    @Bean
    public SecurityWebFilterChain customizeFilter(ServerHttpSecurity httpSecurity){

        httpSecurity
                .csrf(data->data.disable())
                .authorizeExchange(exchange->exchange
                        .pathMatchers("/bookOrder/**", "/book/**")
                        .permitAll()
                        .anyExchange().authenticated()
                );

                return httpSecurity.build();

    }


    /*@Bean
    public SecurityWebFilterChain securityFiterChain(ServerHttpSecurity http){
        http
                .authorizeExchange(exchanges ->  exchanges
                                .pathMatchers( "/bookOrder/**","/book/**").permitAll()
                                //.anyExchange().authenticated()
                        );
        //http.csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();
    }*/

//.cors(corsSpec -> corsSpec.configurationSource(corsConfigurationSource()))

    /*.oauth2ResourceServer(oauth2ResourceServerSpec -> oauth2ResourceServerSpec
                        .jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(grantedAuthoritiesExtractor())))*/


    /*@Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                // Disable CSRF protection for testing or non-browser clients
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                        .requestMatchers("/bookOrder/hi", "/bookOrder/hi/").permitAll()

                )
                //.httpBasic(Customizer.withDefaults())
                .sessionManagement(data->data.sessionCreationPolicy(SessionCreationPolicy.NEVER));

        return http.build();
    }*/


}
