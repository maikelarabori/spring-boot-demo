package com.app.demo.security.repository;

import com.app.demo.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.crypto.password.PasswordEncoder;

@RepositoryEventHandler(User.class)
public class UserEventHandler {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @HandleBeforeCreate
  public void handleBeforeCreate(final User user){
    user.setPassword(passwordEncoder.encode(user.getPassword()));
  }
}
