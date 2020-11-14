package SimpleSBApps.bronzeage.auth;

import java.util.Optional;

public interface ApplicationUserDao {

    public Optional<ApplicationUser> getUserByUsername (String username);
}
