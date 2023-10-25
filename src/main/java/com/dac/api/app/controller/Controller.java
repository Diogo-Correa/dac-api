package com.dac.api.app.controller;

import java.util.List;
import java.util.Optional;

public interface Controller<T, D> {
    List<T> index();

    Optional<T> show(Long id);
}
