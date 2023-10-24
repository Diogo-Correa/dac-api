package com.dac.api.app.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dac.api.app.model.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
