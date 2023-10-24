package com.dac.api.app.controller.user;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dac.api.app.controller.Controller;
import com.dac.api.app.model.user.User;
import com.dac.api.app.repository.user.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController implements Controller {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> index() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User show(@PathVariable Long id) {
        return userRepository.getReferenceById(id);
    }
}