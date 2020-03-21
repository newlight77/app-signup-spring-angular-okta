package io.github.newlight77.bootstrap.config

import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.csrf.CookieCsrfTokenRepository
import org.springframework.security.web.util.matcher.RequestMatcher


@EnableWebSecurity
class SecurityConfiguration : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        //@formatter:off
        http
            .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/api/notes/**").permitAll()
//                .antMatchers(HttpMethod.GET,"/api/notes/**").permitAll()
//                .antMatchers(HttpMethod.DELETE,"/api/notes/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST,"/api/notes").permitAll()
                .antMatchers(HttpMethod.GET,"/rest/notes").permitAll()
                .antMatchers(HttpMethod.POST,"/rest/notes").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();

        http.requiresChannel()
            .requestMatchers(RequestMatcher {
                r -> r.getHeader("X-Forwarded-Proto") != null
            }).requiresSecure()

        http.csrf()
//            .disable();
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        //@formatter:on
    }

    @Bean
    override fun userDetailsService(): UserDetailsService? {
        val user: UserDetails = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("ADMIN")
                .build()
        return InMemoryUserDetailsManager(user)
    }
}