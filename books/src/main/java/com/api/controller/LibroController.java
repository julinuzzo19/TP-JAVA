package com.api.controller;

import com.api.dto.BusquedaLibroResponse;
import com.api.dto.ComentarioDTO;
import com.api.dto.DespublicarDTO;
import com.api.model.Comentario;
import com.api.service.LibrosService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.api.model.Publicacion;
import com.api.dto.Libro;
import com.api.service.PublicacionService;

import java.io.IOException;
import java.util.List;


@RestController
@Api(value="libros", description="operacion para buscar libros")
public class LibroController {

    private LibrosService librosService;

    private PublicacionService publicService;

    @RequestMapping(value="/info", method = RequestMethod.GET)
    public String info()throws IOException {
        return "prueba info";
    }

    @Autowired
    public LibroController( LibrosService librosService, PublicacionService publicService ) {
        this.librosService = librosService;
        this.publicService = publicService;
    }

     @RequestMapping(value="/buscarLibros/{textoBusqueda}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public List<Libro> registration(@PathVariable String textoBusqueda)throws IOException {
         List<Libro> resultado = librosService.buscadorLibro(textoBusqueda);
        return resultado;
    }

    @RequestMapping(value="/publicar/{opinion}", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    public Publicacion guardarPublicacion(@RequestBody Libro libro, @PathVariable String opinion)throws IOException {
        Publicacion publicacion = publicService.savePublicacion(libro, libro.uuidUser, opinion);
        return publicacion;
    }

    @RequestMapping(value="/despublicar", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    public Boolean despublicar(@PathVariable DespublicarDTO publicacion )throws IOException {
        Boolean resultado = publicService.despublicar(publicacion);
        return resultado;
    }
    @RequestMapping(value="/comentar", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    public Comentario comentarPublicacion(@RequestBody ComentarioDTO comentario)throws IOException {
        Comentario publicacion = publicService.guardarComentario(comentario);
        return publicacion;
    }

    @RequestMapping(value="/eliminarComentario", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    public Boolean despublicar(@RequestBody ComentarioDTO comentario )throws IOException {
        Boolean resultado = publicService.eliminarComentario(comentario);
        return resultado;
    }
}
