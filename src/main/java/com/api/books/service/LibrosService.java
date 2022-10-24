package com.api.books.service;

import com.api.books.model.Role;
import com.api.books.model.User;
import com.api.books.repository.RoleRepository;
import com.api.books.repository.UserRepository;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

@Service("librosService")
public class LibrosService {
    private UserRepository userRepository;

    private final String URL = "https://books.googleapis.com/books/v1/volumes?key=AIzaSyBcSi7HTdpcZ2LPXpsL9p8mQnbf103aQa0&q=";

    private HttpClient httpClient = HttpClients.createDefault();

    @Autowired
    public LibrosService() {
    }

    public String buscadorLibro(String texto) {
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
        System.out.println(result);
        return result;
    }
}