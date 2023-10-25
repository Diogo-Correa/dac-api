package com.dac.api.app.service;

import java.util.List;
import java.util.Optional;

public interface Service<T> {
    T save(T data);

    List<T> findAll();

    Optional<T> findById(Long id);

    T update(T product);

    void deleteById(Long id);
}
