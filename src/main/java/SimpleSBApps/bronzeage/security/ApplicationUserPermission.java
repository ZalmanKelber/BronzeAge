package SimpleSBApps.bronzeage.security;

public enum ApplicationUserPermission {
    HERO_READ("hero:read"),
    HERO_WRITE("hero:write"),
    PROPHECY_READ("prophecy:read"),
    PROPHECY_WRITE("prophecy:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
