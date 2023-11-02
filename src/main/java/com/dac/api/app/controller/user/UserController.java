package com.dac.api.app.controller.user;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dac.api.app.controller.Controller;
import com.dac.api.app.model.user.User;
import com.dac.api.app.service.user.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "User endpoints")
@RestController
@RequestMapping("/api/users")
public class UserController implements Controller<User> {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<User> index() {
        return this.userService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> show(@PathVariable Long id) {
        return this.userService.findById(id);
    }

    @PostMapping("/")
    public User create(@Valid @RequestBody User entity) {
        return this.userService.save(entity);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @Valid @RequestBody User entity) {
        return this.userService.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.userService.deleteById(id);
    }
}