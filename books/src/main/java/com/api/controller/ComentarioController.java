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


@RestController
//@Api(value="Comentario", description="operacion para hacer comentario en una publicacion")
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
    public Comentario comentarPublicacion(@RequestBody ComentarioDTO comentario)throws IOException {
        Comentario publicacion = publicService.guardarComentario(comentario);
        return publicacion;
    }
/*
* este metodo es para eliminar los comentarios, solo
* lo pueden eliminar el creador del comentario y el duenio de la publicadio
* falta terminar la logica
* */
    @RequestMapping(value="/eliminarComentario/{idComentario}", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    public Boolean eliminarComentario(@RequestBody String uuid, @PathVariable String idComentario)throws IOException {
        Boolean resultado = publicService.eliminarComentario(uuid, idComentario);
        return resultado;
    }

    /*metodo para modificar la descripcion del comentaroio*/
    @RequestMapping(value="/modificarComentario", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    public Comentario modificarComentario(@PathVariable ModPublicacionDTO modComentario)throws IOException {
        Comentario resultado = publicService.modificarComentario(modComentario);
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
