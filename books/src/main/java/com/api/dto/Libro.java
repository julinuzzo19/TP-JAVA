package com.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Libro {
    public int id;

    public String isbn;

    public String titulo;

    public List<String> autor;

    public String linkLibro;

    public String edicion;

    public String estado;

    public String imagen;

    public String resumen;

    public String[] genero;

    public String uuidUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getAutor() {
        return autor;
    }
    public String getAutorString() {
        return autor.toString();
    }
    public void setAutor(List<String> autor) {
        this.autor = autor;
    }

    public String getLinkLibro() {
        return linkLibro;
    }

    public void setLinkLibro(String linkLibro) {
        this.linkLibro = linkLibro;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String[] getGenero() {
        return genero;
    }

    public void setGenero(String[] genero) {
        this.genero = genero;
    }

}
