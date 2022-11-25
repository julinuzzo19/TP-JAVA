package com.api.controller;

import com.api.dto.*;
import com.api.service.LibrosService;
import com.api.service.AnalizarOpinionService;
//import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.api.model.Publicacion;
import com.api.service.PublicacionService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
public class PublicacionController {

    private LibrosService librosService;

    private PublicacionService publicService;


    @RequestMapping(value="/info", method = RequestMethod.GET)
    public String info(@RequestAttribute String user_id)throws IOException {

        return user_id;
    }

    @Autowired
    public PublicacionController(LibrosService librosService, PublicacionService publicService, AnalizarOpinionService analizarService) {
        this.librosService = librosService;
        this.publicService = publicService;
    }

    /*metodo para realizar la busqueda de libro mediante algun texto*/
     @RequestMapping(value="/buscarLibros/{textoBusqueda}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public List<Libro> buscarLibros(@PathVariable String textoBusqueda)throws IOException {

         List<Libro> resultado = librosService.buscadorLibro(textoBusqueda);

        return resultado;
    }

    /*metodo para publicar el libro encontrado agregandole una opinion*/
    @RequestMapping(value="/publicar/{opinion}", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    public Publicacion guardarPublicacion(@RequestAttribute String user_id,@RequestBody Libro libro, @PathVariable String opinion)throws IOException {
        Publicacion publicacion = publicService.savePublicacion(libro,user_id, opinion);

        return publicacion;
    }
    /*
        metodo para despublicar una publicacion, solo se hace una baja logica
    * se le cambia de estado a despublicar
    * */
    @RequestMapping(value="/despublicar", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    public Boolean despublicar(@RequestAttribute String user_id,@PathVariable DespublicarDTO publicacion )throws IOException {
        publicacion.setUser_id(user_id);
        Boolean resultado = publicService.despublicar(publicacion);
        System.out.print(user_id);
        return resultado;
    }

    /*metodo para modificar la descripcion de la publicacion*/
    @RequestMapping(value="/modificarPublicacion/{idPublicacion}", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    public Publicacion modificarPublicacion(@RequestAttribute String user_id,@PathVariable ModPublicacionDTO modPublic)throws IOException {
        modPublic.setUuidUsuario(user_id);
        Publicacion resultado = publicService.modificarPublicacion(modPublic);


        return resultado;
    }

    /*
    * este metodo es para ver todas las publicaciones que esten publicadas de todos los usuario*/
    @RequestMapping(value="/publicaciones", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public List<PublicacionCompleta> verPublicaciones()throws IOException {
        List<PublicacionCompleta> publicacion = publicService.mostrarPublicaciones();
        return publicacion;
    }

}
