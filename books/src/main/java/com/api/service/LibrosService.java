package com.api.service;


import com.api.dto.BusquedaLibroResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.dto.Libro;
import java.util.List;
import java.util.*;
import com.api.dto.LibroResponse;


import java.io.IOException;

@Service("librosService")
public class LibrosService {
    private final String URL = "https://books.googleapis.com/books/v1/volumes?key=AIzaSyBcSi7HTdpcZ2LPXpsL9p8mQnbf103aQa0&q=";

    private HttpClient httpClient = HttpClients.createDefault();
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public LibrosService() {
    }

    public List<Libro> buscadorLibro(String texto) {
        String url = this.URL+texto.replace(" ","");
        HttpGet request = new HttpGet(url);
        HttpResponse response = null;
        try {
            response = httpClient.execute(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String result = null;
        try {
            result = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BusquedaLibroResponse libroEncontrados = new BusquedaLibroResponse();
        List<Libro> respuesta= new ArrayList<Libro>();
        try {
            libroEncontrados = mapper.readValue(result, BusquedaLibroResponse.class);
            respuesta = this.mapperLibro(libroEncontrados);

            return respuesta;

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Libro> mapperLibro(BusquedaLibroResponse response){
        List<Libro> respuesta= new ArrayList<Libro>();
        for (LibroResponse i : response.getItems()){
            Libro libro = new Libro();
            libro.setTitulo(i.getVolumeInfo().getTitle());
            libro.setAutor(i.getVolumeInfo().getAuthors());
            libro.setResumen(i.getVolumeInfo().getDescription());
            libro.setIsbn(String.valueOf(i.getVolumeInfo().getIndustryIdentifiers().get(0)));
            libro.setLinkLibro(i.getVolumeInfo().getInfoLink());
            libro.setEdicion(i.getVolumeInfo().getPublishedDate()+" "+ i.getVolumeInfo().getPublisher());
            if(i.getVolumeInfo().getImageLinks() != null){
                libro.setImagen(i.getVolumeInfo().getImageLinks().getThumbnail());
            }
            if(i.getVolumeInfo().getCategories() != null){
                libro.setGenero(i.getVolumeInfo().getCategories().toArray(new String[0]));
            }
            respuesta.add(libro);
        }
        return respuesta;
    }
}