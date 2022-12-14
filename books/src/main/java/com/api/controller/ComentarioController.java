package com.api.controller;

import com.api.dto.*;
import com.api.model.Comentario;
import com.api.service.AnalizarOpinionService;
import com.api.service.LibrosService;
import com.api.service.PublicacionService;
//import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import javassist.NotFoundException;
import javassist.tools.web.BadHttpRequest;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


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
    @Operation(summary = "/comentar", security = @SecurityRequirement(name = "bearerAuth"))
    @RequestMapping(value="/comentar", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> comentarPublicacion(@RequestBody ComentarioDTO comentarioBody, @RequestAttribute String user_id)throws IOException {

        try{
            comentarioBody.setUser_id((user_id));
            Comentario comentario = publicService.guardarComentario(comentarioBody);
            if (comentario!=null)
            {
                return new ResponseEntity<>(comentario, HttpStatus.CREATED);
            }

                throw new BadHttpRequest();
        }
        catch (BadHttpRequest e){
            return new ResponseEntity<>("Error al crear comentario", HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
/*
* este metodo es para eliminar los comentarios, solo
* lo pueden eliminar el creador del comentario y el duenio de la publicadio
* falta terminar la logica
* */
@Operation(summary = "/eliminarComentario", security = @SecurityRequirement(name = "bearerAuth"))
@RequestMapping(value="/eliminarComentario/{idComentario}", method = RequestMethod.DELETE, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> eliminarComentario(@PathVariable String idComentario,@RequestAttribute String user_id)throws IOException {

        try {
            Boolean resultado = publicService.eliminarComentario(user_id, idComentario);
            if (resultado)
            {
                return new ResponseEntity<>("Se ha eliminado el comentario correctamente", HttpStatus.OK);

            }
                throw new NotFoundException("No se ha encontrado el comentario a eliminar");
        }
        catch (NotFoundException e){
            return new  ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new   ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*metodo para modificar la descripcion del comentaroio*/
    @Operation(summary = "/modificarComentario/", security = @SecurityRequirement(name = "bearerAuth"))
    @RequestMapping(value="/modificarComentario/{idComentario}", method = RequestMethod.PUT, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> modificarComentario(@RequestBody ModComentarioDTO modComentario,@PathVariable Integer idComentario,@RequestAttribute String user_id)throws IOException {
        try{
            Comentario resultado = publicService.modificarComentario(modComentario, user_id,idComentario);
            if (resultado != null)
            {
                return new ResponseEntity<>(resultado, HttpStatus.OK);

            }
            throw new NotFoundException("No se ha encontrado el comentario a actualizar");
        }
        catch (NotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
