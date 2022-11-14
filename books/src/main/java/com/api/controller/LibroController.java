package com.api.controller;

import com.api.dto.BusquedaLibroResponse;
import com.api.service.LibrosService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@Api(value="libros", description="operacion para buscar libros")
public class LibroController {

    private LibrosService librosService;

    @RequestMapping(value="/info", method = RequestMethod.GET)
    public String info()throws IOException {
        return "prueba info";
    }

    @Autowired
    public LibroController( LibrosService librosService ) {
        this.librosService = librosService;
    }

     @RequestMapping(value="/buscarLibros/{textoPrueba}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
//    @GetMapping(path="/buscarLibros/{textoPrueba}")
    public BusquedaLibroResponse registration(@PathVariable String textoPrueba)throws IOException {
        BusquedaLibroResponse prueba = librosService.buscadorLibro(textoPrueba);
        System.out.println(prueba);
        return prueba;
    }
}
