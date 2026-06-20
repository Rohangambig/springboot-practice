package com.entire.demo.Model.Enum;

import java.security.Permission;
import java.util.HashSet;
import java.util.Set;

public enum Role {
    ADMIN(Set.of(Permissions.WRITE,Permissions.DELETE,Permissions.READ)),
    USER(Set.of(Permissions.READ,Permissions.WRITE));

    private final Set<Permissions> permissions;

    Role(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<Permissions> getPermission() {
        return this.permissions;
    }
}
