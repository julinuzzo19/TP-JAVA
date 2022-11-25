package com.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Setter
@Getter
public class ComentarioDTO {
    int id_comentario;
    String comentario;
    int idPublicacion;
    String user_id;
}
