package ru.mironov.projects.NoPassword.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.mironov.projects.NoPassword.services.UserDetailsServiceImpl;

@EnableWebSecurity
@Configuration
public class SecurityConfig{
    private final UserDetailsServiceImpl userDetailsServiceImpl;


    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/admin").hasRole("ADMIN")
//                .antMatchers("/login", "/registration", "/error", "/api/**").anonymous()
//                .anyRequest().hasAnyRole("USER", "ADMIN")
//                .and()
//                .formLogin().loginPage("/login")
//                .loginProcessingUrl("/process_login")
//                .defaultSuccessUrl("/user", true)
//                .failureUrl("/login?error")
//                .and()
//                .logout().logoutUrl("/logout").logoutSuccessUrl("/login");
//    }
    //    @Bean
//    public UserDetailsService userDetailsService(){
//        return userDetailsServiceImpl.loadUserByUsername("hehe").;
//    }

    //Настройка аутентификации
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsServiceImpl)
//                .passwordEncoder(getPasswordEncoder());
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/login").permitAll()
                        .anyRequest().hasAnyRole("USER", "ADMIN"))
                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/user")
                        .failureUrl("/login?error"))
                .logout(logout -> logout
                        .logoutUrl("/logout").logoutSuccessUrl("/login"))
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    @Primary
    public AuthenticationManagerBuilder configureAuthenticationManagerBuilder(
            AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsServiceImpl).passwordEncoder(encoder());
        return authenticationManagerBuilder;
    }


    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
