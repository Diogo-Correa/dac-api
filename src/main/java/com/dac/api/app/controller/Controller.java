package com.dac.api.app.controller;

import java.util.List;

public interface Controller<T, D> {
    public List<T> index();

    public T show(Long id);
}
