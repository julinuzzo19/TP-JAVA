package com.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "publicacion")
@Getter
@Setter
public class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "publicacion_id")
    private int id;

    @Column
    private String isbn;

    @Column
    private String titulo;

    @Column
    private String autor;

    @Column
    private String linkLibro;

    @Column
    private String uuidUsuario;

    @Column
    private String edicion;

    @Column
    private String estado;

    @Column
    private String imagen;

    @Column
    private String resumen;
    @Column
    private String[] genero;

    @Column
    private String descripcion;

    public Publicacion() {
    }

    public Publicacion(String isbn, String[] genero, String titulo, String autor,String imagen
    ,String estado, String edicion, String uuidUsuario, String linkLibro, String resumen, String descripcion) {
        this.genero = genero;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.edicion = edicion;
        this.estado =estado;
        this.imagen = imagen;
        this.linkLibro = linkLibro;
        this.uuidUsuario = uuidUsuario;
        this.resumen = resumen;
        this.descripcion = descripcion;
    }
}
