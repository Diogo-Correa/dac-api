package com.dac.api.app.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dac.api.app.model.user.User;
import com.dac.api.app.repository.user.UserRepository;
import com.dac.api.app.service.Service;

@org.springframework.stereotype.Service
public class UserService implements Service<User> {

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

}
