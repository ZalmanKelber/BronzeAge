package SimpleSBApps.bronzeage.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

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

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return simpleGrantedAuthorities;
    }
}
