package com.api.repository;

import com.api.model.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("publicacionRepository")
public interface PublicacionRepository extends JpaRepository<Publicacion, Integer> {

}
