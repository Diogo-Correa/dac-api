package com.dac.api.app.controller;

import org.springframework.http.ResponseEntity;

import com.dac.api.app.dto.ApiResponseDTO;

public interface Controller<D> {
    ResponseEntity<ApiResponseDTO> index();

    ResponseEntity<ApiResponseDTO> show(Long id);

    ResponseEntity<ApiResponseDTO> create(D dto);

    ResponseEntity<ApiResponseDTO> update(Long id, D dto);

    ResponseEntity<ApiResponseDTO> delete(Long id);
}
