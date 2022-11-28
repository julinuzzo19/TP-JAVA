package com.api.controller;

import com.api.dto.*;
import com.api.service.LibrosService;
import com.api.service.AnalizarOpinionService;
//import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import javassist.NotFoundException;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.api.model.Publicacion;
import com.api.service.PublicacionService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
public class PublicacionController {

    private LibrosService librosService;

    private PublicacionService publicService;


    @Autowired
    public PublicacionController(LibrosService librosService, PublicacionService publicService, AnalizarOpinionService analizarService) {
        this.librosService = librosService;
        this.publicService = publicService;
    }

    /*metodo para realizar la busqueda de libro mediante algun texto*/
     @RequestMapping(value="/buscarLibros/{textoBusqueda}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> buscarLibros(@PathVariable String textoBusqueda) {

    try{
         List<Libro> resultado = librosService.buscadorLibro(textoBusqueda);

         if (resultado.size() > 0 )
         {
             return new ResponseEntity<>(resultado, HttpStatus.OK);
         }

         throw new NotFoundException("No se han encontrado libros con esa busqueda");

     }
        catch (NotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
        catch (Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

    /*metodo para publicar el libro encontrado agregandole una opinion*/
    @Operation(summary = "/publicar", security = @SecurityRequirement(name = "bearerAuth"))
    @RequestMapping(value="/publicar", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> guardarPublicacion(@RequestAttribute String user_id,@RequestBody PublicarDTO publicacionBody)throws IOException {
        try {
        Publicacion publicacion = publicService.savePublicacion(publicacionBody,user_id);
        if (publicacion != null)
        {
            return new ResponseEntity<>(publicacion, HttpStatus.CREATED);
        }
        throw new BadHttpRequest();

    }
        catch (BadHttpRequest e){
        return new ResponseEntity<>("No se ha podido publicar correctamente", HttpStatus.BAD_REQUEST);
    }
        catch (Exception e){
        return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }
    /*
        metodo para despublicar una publicacion, solo se hace una baja logica
    * se le cambia de estado a despublicar
    * */
    @Operation(summary = "/despublicar/", security = @SecurityRequirement(name = "bearerAuth"))
    @RequestMapping(value="/despublicar/{idPublicacion}", method = RequestMethod.DELETE, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> despublicar(@PathVariable String idPublicacion, @RequestAttribute String user_id){
    try {
        Boolean resultado = publicService.despublicar(idPublicacion,user_id);

        if (resultado != null)
        {
            return new ResponseEntity<>("Publicacion eliminada correctamente", HttpStatus.OK);
        }

        throw new BadHttpRequest();
    }
        catch (BadHttpRequest e){
        return new ResponseEntity<>("No se ha podico eliminar la publicacion", HttpStatus.BAD_REQUEST);

    }
        catch (Exception e){
        return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*metodo para modificar la descripcion de la publicacion*/
    @Operation(summary = "/modificarPublicacion/", security = @SecurityRequirement(name = "bearerAuth"))
    @RequestMapping(value="/modificarPublicacion/{idPublicacion}", method = RequestMethod.PUT, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> modificarPublicacion(@RequestAttribute String user_id,@RequestBody ModPublicacionDTO modPublic, @PathVariable Integer idPublicacion) {
    try{

        Publicacion resultado = publicService.modificarPublicacion(modPublic,idPublicacion,user_id);

        if (resultado != null)
        {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        }
        throw new BadHttpRequest();
    }
        catch (BadHttpRequest e){
        return new ResponseEntity<>("No se ha podido modificar la publicacion", HttpStatus.BAD_REQUEST);

    }
        catch (Exception e){
        return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

    /*
    * este metodo es para ver todas las publicaciones que esten publicadas de todos los usuario*/
    @RequestMapping(value="/publicaciones", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> verPublicaciones() {

       try{
        List<PublicacionCompleta> publicacion = publicService.mostrarPublicaciones();

        if (publicacion.size() > 0 )
        {
            return new ResponseEntity<>(publicacion, HttpStatus.OK);
        }

        throw new NotFoundException("No se han encontrado publicaciones");
    }
        catch (NotFoundException e){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.NOT_FOUND);
    }
        catch (Exception e){
           System.out.print(e.getMessage());
        return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

    @RequestMapping(value="/publicaciones/{usuarioId}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> verPublicaciones(@PathVariable String usuarioId) {
        try{
        List<PublicacionCompleta> publicacion = publicService.verPublicacionUsuario(usuarioId);
        if (publicacion != null) {
            return new ResponseEntity<>(publicacion, HttpStatus.OK);
        }
            throw new NotFoundException("No se han encontrado publicaciones del usuario");
    }
        catch (NotFoundException e){
        return new ResponseEntity<>( e.getMessage(), HttpStatus.NOT_FOUND);
    }
        catch (Exception e){
        return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

}
