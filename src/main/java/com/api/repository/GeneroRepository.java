package com.api.repository;

import com.api.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("generoRepository")
public interface GeneroRepository extends JpaRepository<Genero, Integer> {

}
