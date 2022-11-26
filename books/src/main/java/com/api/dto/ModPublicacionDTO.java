package com.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Optional;

@Setter
@Getter
public class ModPublicacionDTO {
    String descripcion;

     String isbn;


     String titulo;


     String autor;


     String linkLibro;


     String uuidUsuario;


     String edicion;


     String estado;


     String imagen;


     String resumen;

     String[] genero;
}
