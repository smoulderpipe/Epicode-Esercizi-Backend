package it.epicode.gestionedispositivi.configuration;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity
public class AppConfig {

   /* @Bean
    public Cloudinary getCloudinary(@Value("${cloudinary.name}") String name,
                                    @Value("${cloudinary.apikey}") String apikey,
                                    @Value("${cloudinary.secret}") String secret){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", name);
        config.put("api_key", apikey);
        config.put("api_secret", secret);
        return new Cloudinary(config);

    }*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin(http->http.disable());
        httpSecurity.csrf(http->http.disable());
        httpSecurity.sessionManagement(http->http.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity.authorizeHttpRequests(http -> http.requestMatchers("/**").permitAll());

        httpSecurity.authorizeHttpRequests(http->http.requestMatchers(HttpMethod.POST,"/auth/**").permitAll());

        //nega l'accesso a qualsiasi servizio che non sia get e path /api/users
        httpSecurity.authorizeHttpRequests(http->http.anyRequest().authenticated());
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean//permette di abilitare l'accesso al servizio anche da parte di server diversi da quello su cui risiede
    //il servizio. In questo caso ho abilitato tutti i server ad accedere a tutti i servizi
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }


}
