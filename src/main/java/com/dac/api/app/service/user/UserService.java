package com.dac.api.app.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.dac.api.app.model.user.User;
import com.dac.api.app.repository.user.UserRepository;
import com.dac.api.app.service.Service;

@org.springframework.stereotype.Service
public class UserService implements Service<User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User save(User data) {
        return this.userRepository.save(data);
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = this.userRepository.getReferenceById(id);
        return Optional.of(user);
    }

    @Override
    public User update(User data) {
        return this.userRepository.save(data);
    }

    @Override
    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }

}
