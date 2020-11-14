package SimpleSBApps.bronzeage.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {



    private final PasswordEncoder passwordEncoder;

    @Value("${app.remembermekey}")
    String remembermekey;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index", "/css/*", "/js/*", "/login", "/prophecies").permitAll()
                .antMatchers("/api/heroes/*").hasRole(ApplicationUserRole.HERO.name())
//                .antMatchers(HttpMethod.DELETE, "/api/management/**").hasAuthority(ApplicationUserPermission.PROPHECY_WRITE.getPermission())
//                .antMatchers(HttpMethod.POST, "/api/management/**").hasAuthority(ApplicationUserPermission.PROPHECY_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT, "/api/management/**").hasAuthority(ApplicationUserPermission.PROPHECY_WRITE.getPermission())
//                .antMatchers(HttpMethod.GET,"/api/management/**").hasAnyRole(ApplicationUserRole.ADMIN.name(), ApplicationUserRole.ADMIN_TRAINEE.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/prophecies", true)
                .and()
                .rememberMe()
                    .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30))
                    .key(remembermekey);
//        http
//                .csrf()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails oedipusUser = User.builder()
                .username("Oedipus")
                .password(passwordEncoder.encode("man423"))
                .roles(ApplicationUserRole.HERO.name())
                .authorities(ApplicationUserRole.HERO.getGrantedAuthorities())
                .build();

        UserDetails zeusUser = User.builder()
                .username("Zeus")
                .password(passwordEncoder.encode("bull123"))
                .roles(ApplicationUserRole.ADMIN.name())
                .authorities(ApplicationUserRole.ADMIN.getGrantedAuthorities())
                .build();

        UserDetails dionysusUser = User.builder()
                .username("Dionysus")
                .password(passwordEncoder.encode("maenads69"))
                .roles(ApplicationUserRole.ADMIN_TRAINEE.name())
                .authorities(ApplicationUserRole.ADMIN_TRAINEE.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(oedipusUser, zeusUser, dionysusUser);
    }
}
