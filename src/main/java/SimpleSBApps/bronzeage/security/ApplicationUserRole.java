package SimpleSBApps.bronzeage.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static SimpleSBApps.bronzeage.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    HERO(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(HERO_READ, HERO_WRITE, PROPHECY_READ, PROPHECY_WRITE)),
    ADMIN_TRAINEE(Sets.newHashSet(HERO_READ, HERO_WRITE, PROPHECY_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
