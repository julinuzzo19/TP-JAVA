package com.api.service;


import com.api.dto.*;
import com.api.model.Comentario;
import com.api.repository.ComentarioRepository;
import com.google.cloud.language.v1beta2.Sentiment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.repository.PublicacionRepository;
import com.api.model.Publicacion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("publicacionService")
public class PublicacionService {

    @Autowired
    public PublicacionRepository publiRepository;

    @Autowired
    public ComentarioRepository comentRepository;

    @Autowired
    private AnalizarOpinionService analizarService;

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

    public Boolean eliminarComentario(String uuid,String idcomentario) {
        Optional<Comentario> comen = this.comentRepository.findById(Integer.valueOf(idcomentario));
        Comentario coment = new Comentario();
        /*elimina el comentario si es el duenio del comentario o el el duenio de la publicacion*/
        if (uuid.equals(comen.get().getUuidUsuario())){
            comen.get().setEliminado(true);
            coment =  this.comentRepository.save(comen.get());
        } else if (uuid.equals(this.publiRepository.findById(comen.get().getIdPublicacion()))) {
            comen.get().setEliminado(true);
            coment =  this.comentRepository.save(comen.get());
        }
        return coment.isEliminado();
    }

    public List<PublicacionCompleta> mostrarPublicaciones() throws IOException {
        List<Publicacion> publicaciones= this.publiRepository.findAll();
        List<PublicacionCompleta> publis = new ArrayList<PublicacionCompleta>();
        for(Publicacion i : publicaciones){
            if(i.getEstado().equals("publicado")){
                PublicacionCompleta publicCompl = new PublicacionCompleta();
                publicCompl.setPublicacion(i);
                List<Comentario> comentarios = this.comentRepository.findByIdPublicacion(i.getId());
                List<Comentario> comentariosBuenos = new ArrayList<Comentario>();
                for(Comentario c : comentarios){
                    float valor = this.analizarComentario(c.getComentario());
                    if ( valor > 0){
                        comentariosBuenos.add(c);
                    }
                }
                publicCompl.setComentarios(comentariosBuenos);
                publis.add(publicCompl);
            }
        }
        return publis;
    }

    public float analizarComentario(String opinion)throws IOException {
        Sentiment resultado= null;
        float result= 0;
        try {
            resultado = analizarService.analyzeSentimentText(opinion,"es");
            result = resultado.getScore();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public Publicacion modificarPublicacion(ModPublicacionDTO modPublic) {
        Optional<Publicacion> publi = this.publiRepository.findById(Integer.valueOf(modPublic.getIdPublicacion()));
        String usuario = publi.get().getUuidUsuario();
        if(usuario.equals(modPublic.getUuidUsuario())){
            publi.get().setDescripcion(modPublic.getDescripcion());
           return this.publiRepository.save(publi.get());
        }
        return null;
    }

    public Comentario modificarComentario(ModPublicacionDTO modComentario) {
        Optional<Comentario> comen = this.comentRepository.findById(modComentario.getIdPublicacion());
        Comentario coment = new Comentario();
        if (modComentario.getUuidUsuario().equals(comen.get().getUuidUsuario())){
            comen.get().setComentario(modComentario.getDescripcion());
            coment =  this.comentRepository.save(comen.get());
        }
        return coment;
    }
}