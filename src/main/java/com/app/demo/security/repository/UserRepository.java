package com.app.demo.security.repository;

import com.app.demo.security.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {

  User findByUsername(@Param("username") final String username);
}
