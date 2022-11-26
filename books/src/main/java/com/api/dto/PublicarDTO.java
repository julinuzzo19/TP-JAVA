package com.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PublicarDTO {
    public String isbn;

    public String titulo;

    public List<String> autor;

    public String linkLibro;

    public String edicion;

    public String estado;

    public String imagen;

    public String resumen;

    public String[] genero;

    public String opinion;

    //public String uuidUser;

    public PublicarDTO(){}
    public PublicarDTO(String s, String s1, List<String> autors, String s3, String s4, String s5, String s6, String[] strings) {
        this.autor = autors;
    }

}
