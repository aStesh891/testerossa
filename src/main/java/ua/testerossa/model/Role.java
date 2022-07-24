package ua.testerossa.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
  TESTER(Set.of(Permission.READ, Permission.UPDATE)),
  ADMIN(Set.of(Permission.CREATE, Permission.READ, Permission.UPDATE, Permission.DELETE));

  private final Set<Permission> permissions;

  Role(Set<Permission> permissions) {
    this.permissions = permissions;
  }

  public Set<Permission> getPermissions() {
    return permissions;
  }

  public Set<SimpleGrantedAuthority> getAuthorities() {
    return getPermissions().stream()
        .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
        .collect(Collectors.toSet());
  }
}
