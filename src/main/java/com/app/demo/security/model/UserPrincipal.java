package com.app.demo.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

/**
 * The "hard-coded" representation of the UserDetails object
 * used by Spring for authentication/authorizations.
 *
 * @see UserDetails
 */
public class UserPrincipal implements UserDetails {
  private User user;

  public UserPrincipal(final User user) {
    this.user = user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public String getPassword() {
    if (user != null) {
      return user.getPassword();
    }
    return null;
  }

  @Override
  public String getUsername() {
    if (user != null) {
      return user.getUsername();
    }
    return null;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
