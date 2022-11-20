package com.api.service;


import com.api.dto.ComentarioDTO;
import com.api.dto.DespublicarDTO;
import com.api.model.Comentario;
import com.api.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.dto.Libro;
import com.api.repository.PublicacionRepository;
import com.api.model.Publicacion;

import java.util.Optional;

@Service("publicacionService")
public class PublicacionService {

    @Autowired
    public PublicacionRepository publiRepository;

    @Autowired
    public ComentarioRepository comentRepository;

    public Publicacion savePublicacion(Libro libro, String uuidUsuario, String opinion){

        Publicacion publicacion = new Publicacion(libro.getIsbn(), libro.genero, libro.titulo, libro.autor.toString(),
                libro.imagen,"publicado", libro.edicion, uuidUsuario, libro.linkLibro, libro.resumen, opinion);
        Publicacion pu = this.publiRepository.save(publicacion);
        return pu;
    }

    public Boolean despublicar(DespublicarDTO publicacion) {
        Optional<Publicacion> publi = this.publiRepository.findById(publicacion.getIdPublicacion());
        String usuario = publi.get().getUuidUsuario();
        if(usuario.equals(publicacion.getUuidUsuario())){
            publi.get().setEstado("despublicado");
            this.publiRepository.save(publi.get());
            return true;
        }else{
            return false;
        }
    }

    public Comentario guardarComentario(ComentarioDTO comentario) {
        Comentario comen = this.comentRepository.save(new Comentario(comentario.getComentario(), comentario.getIdPublicacion(), comentario.getUuidUsuario()));
        return comen;
    }

    public Boolean eliminarComentario(ComentarioDTO comentario) {
        Optional<Comentario> comen = this.comentRepository.findById(comentario.getId_comentario());


        return null;
    }
}