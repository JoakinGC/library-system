package io.bootify.libreri.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    UserDatilsServiceImplet userDatilsServiceImplet;



    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {



        return httpSecurity
                .csrf(config -> config.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/").permitAll();
                    auth.requestMatchers("/prueba").permitAll();
                    auth.requestMatchers("/cerrarSesion").permitAll();
                    auth.requestMatchers("/documentation/**").permitAll();
                    auth.requestMatchers("/css/**").permitAll();
                    auth.requestMatchers("/images/**").permitAll();
                    auth.requestMatchers("/menuEmpleado").hasRole("EMPLEADO");
                    auth.requestMatchers("/menuSupervisor").hasRole("SUPERVISOR");
                    auth.requestMatchers("/swagger-ui.html").permitAll();
                    auth.requestMatchers("/usuarios/**").hasRole("SUPERVISOR");
                    auth.requestMatchers("/fichados/**").hasRole("SUPERVISOR");
                    auth.requestMatchers("/prestamos/**").hasRole("EMPLEADO");
                    auth.requestMatchers("/ejemplars/**").hasRole("EMPLEADO");
                    auth.requestMatchers("/socios/**").hasRole("EMPLEADO");
                    auth.requestMatchers("/libros/**").hasRole("EMPLEADO");
                    auth.requestMatchers("/revistas/**").hasRole("EMPLEADO");
                    auth.requestMatchers("/autors/**").hasRole("EMPLEADO");
                    auth.requestMatchers("/editorials/**").hasRole("EMPLEADO");
                    auth.requestMatchers("/generos/**").hasRole("EMPLEADO");
                    auth.anyRequest().hasRole("ADMIN");
                })
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .httpBasic()
                .and()
                .build();
    }

    @Bean
    UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(User.withUsername("Joaquin")
                .password("pepe1")
                .roles("ADMIN")
                .build());

        manager.createUser(User.withUsername("Juan")
                .password("pepe2")
                .roles("SUPERVISOR")
                .build());

        manager.createUser(User.withUsername("Ruben")
                .password("pepe3")
                .roles("EMPLEADO")
                .build());

        return manager;

    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity,PasswordEncoder passwordEncoder) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

}


