package SimpleSBApps.bronzeage.auth;

import SimpleSBApps.bronzeage.security.ApplicationUserRole;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("temp")
public class TempApplicationUserDaoService implements ApplicationUserDao{

    private final PasswordEncoder encoder;

    @Autowired
    public TempApplicationUserDaoService(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public Optional<ApplicationUser> getUserByUsername(String username) {
        return getAllUsers().stream().filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    private List<ApplicationUser> getAllUsers() {
        List<ApplicationUser> allUsers = Lists.newArrayList(
                new ApplicationUser(ApplicationUserRole.HERO.getGrantedAuthorities(),
                        "Oedipus",
                        encoder.encode("man423"),
                        true, true, true, true),

                new ApplicationUser(ApplicationUserRole.ADMIN.getGrantedAuthorities(),
                        "Zeus",
                        encoder.encode("bull123"),
                        true, true, true, true),

                new ApplicationUser(ApplicationUserRole.ADMIN_TRAINEE.getGrantedAuthorities(),
                        "Dionysus",
                        encoder.encode("maenads69"),
                        true, true, true, true)

        );

        return allUsers;
    }
}
