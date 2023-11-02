package com.dac.api.app.repository.edition;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dac.api.app.model.edition.Edition;

public interface EditionRepository extends JpaRepository<Edition, Long> {

}
