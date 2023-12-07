package com.dac.api.app.controller.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.dac.api.app.dto.ApiResponseDTO;
import com.dac.api.app.dto.UserSaveDTO;
import com.dac.api.app.model.user.User;
import com.dac.api.app.service.user.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "User endpoints")
@RestController
@RequestMapping("/api/users")
public class UserController implements Controller<UserSaveDTO> {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    @Operation(description = "Endpoint para listagem de usuários.")
    public ResponseEntity<ApiResponseDTO> index() {
        try {
            List<User> users = this.userService.findAll();
            return ResponseEntity.ok(new ApiResponseDTO("List of users", users));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(e.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    @Operation(description = "Endpoint para exibição de usuários.")
    public ResponseEntity<ApiResponseDTO> show(@PathVariable Long id) {
        try {
            Optional<User> user = this.userService.findById(id);
            return ResponseEntity.ok(new ApiResponseDTO("Show user", user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(e.getMessage(), null));
        }
    }

    @PostMapping("/")
    @Operation(description = "Endpoint para criação de usuários.")
    public ResponseEntity<ApiResponseDTO> create(@Valid @RequestBody UserSaveDTO entity) {
        try {
            User user = this.userService.save(entity);
            return ResponseEntity.ok(new ApiResponseDTO("User created", user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(e.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    @Operation(description = "Endpoint para atualização de usuários.")
    public ResponseEntity<ApiResponseDTO> update(@PathVariable Long id, @Valid @RequestBody UserSaveDTO entity) {
        try {
            User user = this.userService.update(id, entity);
            return ResponseEntity.ok(new ApiResponseDTO("User updated", user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Endpoint para remoção de usuários.")
    public ResponseEntity<ApiResponseDTO> delete(@PathVariable Long id) {
        try {
            this.userService.deleteById(id);
            return ResponseEntity.ok(new ApiResponseDTO("User deleted", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(e.getMessage(), null));
        }
    }

    @PatchMapping("/{id}/activity/{activity_id}")
    @Operation(description = "Endpoint para favoritar atividades.")
    public ResponseEntity<ApiResponseDTO> updateFavoriteActivity(@PathVariable Long id,
            @PathVariable Long activity_id) {
        try {
            this.userService.updateFavoriteActivity(id, activity_id);
            return ResponseEntity.ok(new ApiResponseDTO("Activity favorited", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(e.getMessage(), null));
        }
    }
}