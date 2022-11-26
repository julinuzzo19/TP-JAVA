package com.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Setter
@Getter
public class ComentarioDTO {

    String comentario;
    int idPublicacion;
    String user_id;

    public ComentarioDTO(){}

    public ComentarioDTO(String comentario, int idPublicacion, String user){
        this.comentario = comentario;
        this.idPublicacion = idPublicacion;
        this.user_id = user;
    }
}
