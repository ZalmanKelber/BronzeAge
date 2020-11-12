package SimpleSBApps.bronzeage.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/heroes/*").hasRole(ApplicationUserRole.HERO.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails oedipusUser = User.builder()
                .username("Oedipus")
                .password(passwordEncoder.encode("man423"))
                .roles(ApplicationUserRole.HERO.name())
                .build();

        UserDetails zeusUser = User.builder()
                .username("Zeus")
                .password(passwordEncoder.encode("bull123"))
                .roles(ApplicationUserRole.ADMIN.name())
                .build();

        UserDetails dionysusUser = User.builder()
                .username("Dionysus")
                .password(passwordEncoder.encode("maenads69"))
                .roles(ApplicationUserRole.ADMIN_TRAINEE.name())
                .build();

        return new InMemoryUserDetailsManager(oedipusUser, zeusUser, dionysusUser);
    }
}
