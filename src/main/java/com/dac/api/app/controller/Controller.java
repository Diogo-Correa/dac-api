package com.dac.api.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

public interface Controller<T, D> {
    ResponseEntity<List<T>> index();

    ResponseEntity<Optional<T>> show(Long id);

    ResponseEntity<T> create(D dto);

    ResponseEntity<T> update(Long id, D dto);

    void delete(Long id);
}
