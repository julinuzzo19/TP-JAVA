package com.api.books.controller;

import com.api.books.service.LibrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
public class LibroController {

    private LibrosService librosService;


    @Autowired
    public LibroController( LibrosService librosService ) {
        this.librosService = librosService;
    }

    @RequestMapping(value="/libros/{textoPrueba}", method = RequestMethod.GET)
    public String registration(@PathVariable String textoPrueba)throws IOException {
        String prueba = librosService.buscadorLibro(textoPrueba);
        System.out.println(prueba);
        return prueba;
    }
}
