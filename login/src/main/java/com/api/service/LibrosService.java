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

import java.io.IOException;

@Service("librosService")
public class LibrosService {
    private final String URL = "https://books.googleapis.com/books/v1/volumes?key=AIzaSyBcSi7HTdpcZ2LPXpsL9p8mQnbf103aQa0&q=";

    private HttpClient httpClient = HttpClients.createDefault();
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public LibrosService() {
    }

    public BusquedaLibroResponse buscadorLibro(String texto) {
        String url = this.URL+texto;
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
        try {
            libroEncontrados = mapper.readValue(result, BusquedaLibroResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
         System.out.println(libroEncontrados);

        return libroEncontrados;
    }
}