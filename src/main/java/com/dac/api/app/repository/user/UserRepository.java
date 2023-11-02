package com.dac.api.app.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dac.api.app.model.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findByEmail(String email);
}
