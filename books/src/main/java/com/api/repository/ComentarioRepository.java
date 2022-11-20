package com.api.repository;

import com.api.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("comentarioRepository")
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {

}
