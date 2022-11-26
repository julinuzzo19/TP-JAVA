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

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private int id;

        private String isbn;

        private String titulo;

        private String autor;

        private String linkLibro;

        private String uuidUsuario;

        private String edicion;

        private String estado;

        private String imagen;

        private String resumen;

        private String[] genero;

        private String descripcion;

        public Builder id(int id){
            this.id = id;
            return this;
        }


        public Builder isbn(String isbn){
            this.isbn = isbn;
            return this;
        }


        public Builder titulo(String titulo){
            this.titulo = titulo;
            return this;
        }
        public Builder autor(String autor){
            this.autor = autor;
            return this;
        }
        public Builder linkLibro(String linkLibro){
            this.linkLibro = linkLibro;
            return this;
        }
        public Builder uuidUsuario(String uuidUsuario){
            this.uuidUsuario = uuidUsuario;
            return this;
        }
        public Builder genero(String[] genero){
            this.genero = genero;
            return this;
        }

        public Builder estado(String estado){
            this.estado = estado;
            return this;
        }
        public Builder imagen(String imagen){
            this.imagen = imagen;
            return this;
        }
        public Builder resumen(String resumen){
            this.resumen = resumen;
            return this;
        }
        public Builder edicion(String edicion){
            this.edicion = edicion;
            return this;
        }
        public Builder descripcion(String descripcion){
            this.descripcion = descripcion;
            return this;
        }



        public Publicacion build(){
            return new Publicacion(isbn, genero, titulo, autor, imagen
                    , estado, edicion, uuidUsuario, linkLibro, resumen, descripcion );
        }
    }

}
