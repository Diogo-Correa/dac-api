package com.dac.api.app.controller.user;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.dac.api.app.dto.UserResponseDTO;
import com.dac.api.app.dto.UserSaveDTO;
import com.dac.api.app.dto.UserShowResponseDTO;
import com.dac.api.app.model.user.User;
import com.dac.api.app.service.user.UserService;
import com.dac.api.app.util.GenericMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "User endpoints")
@RestController
@RequestMapping("/api/users")
public class UserController implements Controller<UserSaveDTO> {

    @Autowired
    private UserService userService;

    @Autowired
    private GenericMapper genericMapper;

    @GetMapping("/")
    @Operation(description = "Endpoint para listagem de usuários.")
    public ResponseEntity<ApiResponseDTO> index() {
        try {
            List<User> users = this.userService.findAll();
            List<UserResponseDTO> usersResponse = users.stream()
                    .map(user -> this.genericMapper.toDTO(user, UserResponseDTO.class))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new ApiResponseDTO("List of users", usersResponse));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(e.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    @Operation(description = "Endpoint para exibição de usuários.")
    public ResponseEntity<ApiResponseDTO> show(@PathVariable Long id) {
        try {
            Optional<User> user = this.userService.findById(id);

            UserShowResponseDTO response = this.genericMapper.toDTO(user, UserShowResponseDTO.class);

            return ResponseEntity.ok(new ApiResponseDTO("Show user", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(e.getMessage(), null));
        }
    }

    @PostMapping("/")
    @Operation(description = "Endpoint para criação de usuários.")
    public ResponseEntity<ApiResponseDTO> create(@Valid @RequestBody UserSaveDTO entity) {
        try {
            User user = this.userService.save(entity);
            UserShowResponseDTO response = this.genericMapper.toDTO(user, UserShowResponseDTO.class);
            return ResponseEntity.ok(new ApiResponseDTO("User created", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(e.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    @Operation(description = "Endpoint para atualização de usuários.")
    public ResponseEntity<ApiResponseDTO> update(@PathVariable Long id, @Valid @RequestBody UserSaveDTO entity) {
        try {
            User user = this.userService.update(id, entity);
            UserShowResponseDTO response = this.genericMapper.toDTO(user, UserShowResponseDTO.class);
            return ResponseEntity.ok(new ApiResponseDTO("User updated", response));
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
            User user = this.userService.updateFavoriteActivity(id, activity_id);
            UserShowResponseDTO response = this.genericMapper.toDTO(user, UserShowResponseDTO.class);
            return ResponseEntity.ok(new ApiResponseDTO("Activity favorited", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO(e.getMessage(), null));
        }
    }
}