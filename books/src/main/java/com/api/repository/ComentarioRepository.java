package com.api.repository;

import com.api.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("comentarioRepository")
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {


    List<Comentario> findByIdPublicacion(int idPublicacion);
    List<Comentario> findByIdPublicacionAndEliminado(int idPublicacion,Boolean eliminado);

}
