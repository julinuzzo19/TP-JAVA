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

    public PublicacionService(PublicacionRepository publicacionRepository, ComentarioRepository comentRepository) {
        this.publiRepository = publicacionRepository;
        this.comentRepository = comentRepository;
    }


    public Publicacion savePublicacion(PublicarDTO libro, String uuidUsuario){
        try{
        Publicacion publicacion = new Publicacion(libro.getIsbn(), libro.genero, libro.titulo, libro.autor.toString(),
                libro.imagen,"publicado", libro.edicion, uuidUsuario, libro.linkLibro, libro.resumen, libro.getOpinion());
        Publicacion pu = this.publiRepository.save(publicacion);
        return pu;
        }
        catch(Exception e)
        {
        return null;
        }
    }

    public Boolean despublicar(String idpublicacion, String user_id) {
        try {
        Optional<Publicacion> publi = this.publiRepository.findById(Integer.valueOf(idpublicacion));
        String usuario = publi.get().getUuidUsuario();
        if(usuario.equals(user_id)){
            publi.get().setEstado("despublicado");
            this.publiRepository.save(publi.get());
            return true;
        }
            return null;
        }
        catch(Exception e)
        {
            return null;
        }
    }


    public Comentario guardarComentario(ComentarioDTO comentario) {
        try {
            Comentario c = new Comentario(comentario.getComentario(), comentario.getIdPublicacion(), comentario.getUser_id());
            Comentario comen = this.comentRepository.save(c);
            return comen;
        }
        catch (Exception e)
        {
           return null;
        }

    }

    public Boolean eliminarComentario(String uuid,String idcomentario) {
        try{

        Optional<Comentario> comen = this.comentRepository.findById(Integer.valueOf(idcomentario));
        Comentario coment = new Comentario();
        /*elimina el comentario si es el duenio del comentario o el el duenio de la publicacion*/
        if (uuid.equals(comen.get().getUser_id())){
            comen.get().setEliminado(true);
            coment =  this.comentRepository.save(comen.get());
        } else if (uuid.equals(this.publiRepository.findById(comen.get().getIdPublicacion()))) {
            comen.get().setEliminado(true);
            coment =  this.comentRepository.save(comen.get());
        }
        return coment.isEliminado();
        }
        catch(Exception e){
            return false;
        }
    }

    public List<PublicacionCompleta> mostrarPublicaciones() throws IOException {
        List<Publicacion> publicaciones= this.publiRepository.findAll();
        List<PublicacionCompleta> publis = new ArrayList<PublicacionCompleta>();
        for(Publicacion i : publicaciones){
            if(i.getEstado().equals("publicado")){
                PublicacionCompleta publicCompl = new PublicacionCompleta();
                publicCompl.setPublicacion(i);
                List<Comentario> comentarios = this.comentRepository.findByIdPublicacionAndEliminado(i.getId(),false);
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
            return result;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public Publicacion modificarPublicacion(ModPublicacionDTO modPublic,Integer idPublicacion, String user_id) {
        try{

        Optional<Publicacion> publi = this.publiRepository.findById(idPublicacion);
        String usuario = publi.get().getUuidUsuario();
        if(usuario.equals(user_id)){
        if (modPublic.getDescripcion() != null){
            publi.get().setDescripcion(modPublic.getDescripcion());

        }
            if (modPublic.getIsbn() != null){
                publi.get().setIsbn(modPublic.getIsbn());

            }
            if (modPublic.getTitulo() != null){
                publi.get().setTitulo(modPublic.getTitulo());

            }
            if (modPublic.getAutor() != null){
                publi.get().setAutor(modPublic.getAutor());

            }
            if (modPublic.getLinkLibro() != null){
                publi.get().setLinkLibro(modPublic.getLinkLibro());

            }
            if (modPublic.getEdicion() != null){
                publi.get().setEdicion(modPublic.getEdicion());

            }
            if (modPublic.getImagen() != null){
                publi.get().setImagen(modPublic.getImagen());

            }
            if (modPublic.getResumen() != null){
                publi.get().setResumen(modPublic.getResumen());

            }
            if (modPublic.getGenero() != null){
                publi.get().setGenero(modPublic.getGenero());
            }

           return this.publiRepository.save(publi.get());
        }
            return null;

        }
        catch (Exception e)
        {
            System.out.print(e.getMessage());
            return null;
        }
    }

    public Comentario modificarComentario(ModComentarioDTO modComentario, String user_id, Integer comentarioId ) {

        try {
            Optional<Comentario> comen = this.comentRepository.findById(comentarioId);
            Comentario coment = new Comentario();
            if (user_id.equals(comen.get().getUser_id())) {
                comen.get().setComentario(modComentario.getComentario());
                coment = this.comentRepository.save(comen.get());
            }
            return coment;
        }
        catch (Exception e)
        {
            return null;
        }

    }

    public List<PublicacionCompleta> verPublicacionUsuario(String user_id) {
        try{
        List<Publicacion> publicaciones = this.publiRepository.findByUuidUsuario(user_id);
        List<PublicacionCompleta> publis = new ArrayList<PublicacionCompleta>();
        for(Publicacion i : publicaciones){
            if(i.getEstado().equals("publicado")){
                PublicacionCompleta publicCompl = new PublicacionCompleta();
                publicCompl.setPublicacion(i);
                List<Comentario> comentarios = this.comentRepository.findByIdPublicacionAndEliminado(i.getId(),false);
                List<Comentario> comentariosBuenos = new ArrayList<Comentario>();
                for(Comentario c : comentarios){
                    comentariosBuenos.add(c);
                }
                publicCompl.setComentarios(comentariosBuenos);
                publis.add(publicCompl);
            }
        }
        return publis;}
        catch(Exception e)
        {
            return null;
        }
    }
}