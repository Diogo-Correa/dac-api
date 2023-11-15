package com.dac.api.app.service;

import java.util.List;
import java.util.Optional;

public interface Service<T, D> {
    T save(D data);

    List<T> findAll();

    Optional<T> findById(Long id);

    T update(Long id, D data);

    void deleteById(Long id);
}
