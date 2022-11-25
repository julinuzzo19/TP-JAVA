package com.api.controller;

import com.api.dto.*;
import com.api.model.Comentario;
import com.api.service.AnalizarOpinionService;
import com.api.service.LibrosService;
import com.api.service.PublicacionService;
//import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;


@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    private LibrosService librosService;

    private PublicacionService publicService;

    private AnalizarOpinionService analizarService;


    @Autowired
    public ComentarioController(LibrosService librosService, PublicacionService publicService, AnalizarOpinionService analizarService) {
        this.librosService = librosService;
        this.publicService = publicService;
        this.analizarService = analizarService;
    }

    /*metodo para agregar comentario a una publicacion*/
    @RequestMapping(value="/comentar", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    public Comentario comentarPublicacion(@RequestBody ComentarioDTO comentario,@RequestAttribute String user_id)throws IOException {
        comentario.setUser_id((user_id));
        Comentario publicacion = publicService.guardarComentario(comentario);
        return publicacion;
    }
/*
* este metodo es para eliminar los comentarios, solo
* lo pueden eliminar el creador del comentario y el duenio de la publicadio
* falta terminar la logica
* */
    @RequestMapping(value="/eliminarComentario/{idComentario}", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    public Boolean eliminarComentario(@PathVariable String idComentario,@RequestAttribute String user_id)throws IOException {
        Boolean resultado = publicService.eliminarComentario(user_id, idComentario);
        return resultado;
    }

    /*metodo para modificar la descripcion del comentaroio*/
    @RequestMapping(value="/modificarComentario", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    public Comentario modificarComentario(@RequestBody ModPublicacionDTO modComentario,@RequestAttribute String user_id)throws IOException {
        Comentario resultado = publicService.modificarComentario(modComentario, user_id);
        return resultado;
    }
//    @RequestMapping(value="/analizarComentario/{opinion}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
//    public String analizarContenido(@PathVariable String opinion)throws IOException {
//        Sentiment resultado= null;
//        String result= null;
//        try {
//            resultado = analizarService.analyzeSentimentText(opinion,"es");
//            result = String.valueOf(resultado.getMagnitude());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return result;
//    }
}
