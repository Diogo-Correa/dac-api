package com.dac.api.app.service.user;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.dac.api.app.dto.AuthDTO;
import com.dac.api.app.exception.UserFoundException;
import com.dac.api.app.model.user.User;
import com.dac.api.app.repository.user.UserRepository;
import com.dac.api.app.service.Service;

@org.springframework.stereotype.Service
public class UserService implements Service<User> {

    @Value("${security.jwt.secret}")
    private String secretKey;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User save(User data) {

        this.userRepository
                .findByUsernameOrEmail(data.getUsername(), data.getEmail())
                .ifPresent(user -> {
                    throw new UserFoundException();
                });

        var password = this.passwordEncoder.encode(data.getPassword());
        data.setPassword(password);
        return this.userRepository.save(data);
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = this.userRepository.getReferenceById(id);
        return Optional.of(user);
    }

    @Override
    public User update(Long id, User data) {
        User user = this.userRepository.getReferenceById(id);

        if (user == null)
            return null;

        if (data.getEmail() != null)
            user.setEmail(data.getEmail());
        if (data.getUsername() != null)
            user.setUsername(data.getUsername());
        if (data.getPassword() != null) {
            var password = this.passwordEncoder.encode(data.getPassword());
            user.setPassword(password);
        }

        return this.userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }

    public String authenticate(AuthDTO authDTO) throws AuthenticationException {
        User user = this.userRepository.findByEmail(authDTO.getEmail()).orElseThrow(
                () -> {
                    throw new UsernameNotFoundException("Usuário não encontrado");
                });

        var passwordMatches = this.passwordEncoder.matches(authDTO.getPassword(), user.getPassword());

        if (!passwordMatches)
            throw new AuthenticationException("A senha está incorreta");

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var token = JWT.create().withIssuer("dac_api")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withSubject(user.getId().toString())
                .sign(algorithm);

        return token;
    }

}
