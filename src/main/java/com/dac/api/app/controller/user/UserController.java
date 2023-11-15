package com.dac.api.app.controller.user;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dac.api.app.controller.Controller;
import com.dac.api.app.dto.UserSaveDTO;
import com.dac.api.app.model.user.User;
import com.dac.api.app.service.user.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "User endpoints")
@RestController
@RequestMapping("/api/users")
public class UserController implements Controller<User, UserSaveDTO> {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    @Operation(description = "Endpoint para listagem de usuários.")
    public List<User> index() {
        return this.userService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(description = "Endpoint para exibição de usuários.")
    public Optional<User> show(@PathVariable Long id) {
        return this.userService.findById(id);
    }

    @PostMapping("/")
    @Operation(description = "Endpoint para criação de usuários.")
    public User create(@Valid @RequestBody UserSaveDTO entity) {
        return this.userService.save(entity);
    }

    @PutMapping("/{id}")
    @Operation(description = "Endpoint para atualização de usuários.")
    public User update(@PathVariable Long id, @Valid @RequestBody UserSaveDTO entity) {
        return this.userService.update(id, entity);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Endpoint para remoção de usuários.")
    public void delete(@PathVariable Long id) {
        this.userService.deleteById(id);
    }

    @PatchMapping("/{id}/activity/{activity_id}")
    @Operation(description = "Endpoint para favoritar atividades.")
    public User updateFavoriteActivity(@PathVariable Long id, @PathVariable Long activity_id) {
        return this.userService.updateFavoriteActivity(id, activity_id);
    }
}